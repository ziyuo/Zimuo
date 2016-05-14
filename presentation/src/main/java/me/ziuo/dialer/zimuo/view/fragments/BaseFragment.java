package me.ziuo.dialer.zimuo.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangbokai on 2016/3/13.
 */
public abstract class BaseFragment extends Fragment {


    /**
     * 初始化界面布局
     */
    public abstract void initViews(View v);

    protected abstract View initFragmentView(LayoutInflater inflater,
                                             ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initFragmentView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        inflateData();
    }

    protected abstract void inflateData();

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ziuo", "onResume -->className is " + getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ziuo", "onPause -->className is " + getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ziuo", "onDestroy -->className is " + getClass().getSimpleName());

    }

}
