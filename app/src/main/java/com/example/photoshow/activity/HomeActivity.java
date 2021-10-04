package com.example.photoshow.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.photoshow.R;
import com.example.photoshow.adapter.MyPagerAdapter;
import com.example.photoshow.entity.TabEntity;
import com.example.photoshow.fragment.AddFragment;
import com.example.photoshow.fragment.CollectionFragment;
import com.example.photoshow.fragment.FriendsFragment;
import com.example.photoshow.fragment.HomeFragment;
import com.example.photoshow.fragment.MyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;
    private String[] mTitles = {"首页","收藏","添加","好友","我的"};

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int[] mIconUnselectIds = {
            R.mipmap.shouye, R.mipmap.shoucang2,R.mipmap.zengjiatianjiajiahao,
            R.mipmap.haoyou,R.mipmap.wode_1};
    private int[] mIconSelectIds = {
            R.mipmap.shouyetianchong, R.mipmap.shoucang,R.mipmap.tianjia,
            R.mipmap.haoyou_1, R.mipmap.wode};

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewPager);
        commonTabLayout = findViewById(R.id.commomTabLayout);
    }

    @Override
    protected void initData() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(CollectionFragment.newInstance());
        mFragments.add(AddFragment.newInstance());
        mFragments.add(FriendsFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mTitles,mFragments));
    }
}