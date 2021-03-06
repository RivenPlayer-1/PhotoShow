package com.example.photoshow.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoshow.R;
import com.example.photoshow.adapter.ImageListAdapter;
import com.example.photoshow.entity.PhotoItem;
import com.example.photoshow.utils.PickerConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ImagePickerActivity extends AppCompatActivity {

    public static final String TAG = "ImagePickerActivity";
    public static final int LOADER_ALL = 1;// 获取所有图片
    private List<PhotoItem> mDatas = new ArrayList<>();
    private ImageListAdapter mAdapter;
    private TextView mSelectFinished;
    public static final int MAX_IMAGES = 9;// 最大图片数量
    private PickerConfig mPickerConfig;
    private Toolbar toolbar;
    private FloatingActionButton fab;
   public static String[] paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
//        initData();
        initLoadManager();
        initView();
        initEvent();
        initConfig();
    }

    private void initConfig() {
        mPickerConfig = PickerConfig.getInstance();
        mAdapter.setSelectMaxImages(mPickerConfig.getImageSelectCount());
    }

    private void initEvent() {
        // (0/9)完成动态变化
        mAdapter.setOnItemSelectChanged(new ImageListAdapter.OnItemSelectChanged() {
            @Override
            public void changeSelectedCount(List<PhotoItem> selectedImages) {
                if (selectedImages.size() == 0) {
                    mSelectFinished.setEnabled(false);
                    mSelectFinished.setText("完成");
                    return;
                }
                mSelectFinished.setEnabled(true);
                mSelectFinished.setTextColor(Color.WHITE);
                mSelectFinished.setText("("+selectedImages.size()+"/9)完成");
                System.out.println("("+selectedImages.size()+"/9)完成");
            }
        });
        mSelectFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击完成后 得到静态内部实例，将结果回传到主界面，结束界面
                PickerConfig.OnImagesSelectFinishedListener selected = mPickerConfig.getSelectFinishedListener();
                selected.onSelectFinished(mAdapter.getSelectedImages());
                // 当选择完毕，清空列表，避免上次数据造成的缓存问题
//                mAdapter.release();
                finish();
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_picker);
        setSupportActionBar(toolbar);



        mSelectFinished = findViewById(R.id.select_count);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_picker);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ImageListAdapter();
        // 最好不要在这里设置数据源，因为子线程完全获取到数据之前，适配器已经设置上空的数据源了，
        // 解决方法有很多种
        /*Log.d(TAG, "initView: mDatas --> " + mDatas.size());
        adapter.setData(mDatas);*/
        // 设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);
        // 设置适配器
        recyclerView.setAdapter(mAdapter);
    }

    private void initLoadManager() {
        // loadManager.getInstance(this)
        // 注意点：getInstance上下文对象必须是AppCompatActivity才可以
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(LOADER_ALL, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            private final String[] IMAGE_PROJECTION = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATE_ADDED,
                    MediaStore.Images.Media._ID,
            };

            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
                if (i == LOADER_ALL) {
                    return new CursorLoader(ImagePickerActivity.this,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            IMAGE_PROJECTION, null, null, IMAGE_PROJECTION[2]+" DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                int i=0;
                if (cursor != null) {
                    mDatas.clear();
                    while (cursor.moveToNext()) {
                        PhotoItem item = new PhotoItem();
                        String path = cursor.getString(cursor.getColumnIndex(IMAGE_PROJECTION[0]));
                        String name = cursor.getString(cursor.getColumnIndex(IMAGE_PROJECTION[1]));
                        long date = cursor.getLong(cursor.getColumnIndex(IMAGE_PROJECTION[2]));
                        item.setCreateTime(date);
                        item.setName(name);
                        item.setPath(path);
                        mDatas.add(item);
                    }

                    mAdapter.setData(mDatas);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initData() {
        // 获取SD卡的文件，有的比较耗时，一般我们不在Main-Thread线程中去做
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri,new String[]{
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID},null,null,null,null);
        while (cursor.moveToNext()) {
            PhotoItem photoItem = new PhotoItem();
            photoItem.setPath(cursor.getString(0));
            photoItem.setName(cursor.getString(1));
            photoItem.setCreateTime(cursor.getLong(2));
            mDatas.add(photoItem);
        }
        cursor.close();
    }

}
