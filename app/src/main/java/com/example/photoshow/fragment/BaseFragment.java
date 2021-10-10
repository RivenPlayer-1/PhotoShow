package com.example.photoshow.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.photoshow.api.PhotoCallBack;

import static android.content.Context.MODE_PRIVATE;

public class BaseFragment extends Fragment implements PhotoCallBack {
    public void showToast(String msg){
        Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }

    public void navigeteTo(Class cls){
        Intent in = new Intent(getActivity(),cls);
        startActivity(in);
    }

    public void showToastSync(String msg){
        Looper.prepare();
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    protected void saveStringToSp(String key,String val){
        SharedPreferences sp = getActivity().getSharedPreferences("sp_ttit",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, val);
        editor.commit();
    }

    public String getStringFromSp(String key){
        SharedPreferences sp = getActivity().getSharedPreferences("sp_ttit",MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public void navigateToWithFlag(Class cls, int flags) {
        Intent in = new Intent(getActivity(), cls);
        in.setFlags(flags);
        startActivity(in);
    }
    protected void removeByKey(String key) {
        SharedPreferences sp = getActivity().getSharedPreferences("sp_ttit", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.remove(key);
        edit.commit();
    }
    @Override
    public void onSuccess(String res) {

    }

    @Override
    public void onFailure(Exception e) {

    }
}
