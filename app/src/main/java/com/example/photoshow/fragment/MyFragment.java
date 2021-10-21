package com.example.photoshow.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.photoshow.R;
import com.example.photoshow.activity.LoginActivity;
import com.example.photoshow.activity.UpdateActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button send2,send3;
    private TextView usename;
    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my, container, false);

//        RecyclerView recyclerView = v.findViewById(R.id.recycle_view);
//        //初始化布局管理器
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        //选择垂直排列
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        //为recyclerView指定布局管理器
//        recyclerView.setLayoutManager(linearLayoutManager);

        return v;

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        usename=getActivity().findViewById(R.id.username);
        usename.setText(getStringFromSp("uid"));
        send2 = getActivity().findViewById(R.id.bt_mima);
        send3 = getActivity().findViewById(R.id.bt_logout);
        send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("修改密码");
                startActivity(new Intent(getActivity(), UpdateActivity.class));
            }
        });

        send3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeByKey("token");
                navigateToWithFlag(LoginActivity.class,
                        Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                showToast("注销成功");
            }
        });
    }

}
