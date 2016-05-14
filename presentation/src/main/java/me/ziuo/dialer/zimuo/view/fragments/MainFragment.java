package me.ziuo.dialer.zimuo.view.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.ziuo.dialer.zimuo.R;
import me.ziuo.dialer.zimuo.view.adapters.BaseRecyclerAdapter;
import me.ziuo.dialer.zimuo.view.adapters.ContactsRecyclerAdapter;
import me.ziuo.dialer.zimuo.view.adapters.HomeRecyclerAdapter;
import me.ziuo.dialer.zimuo.view.widgets.EmptyRecyclerView;

/**
 * Created by wangbokai on 2016/3/13.
 * 功能界面
 */
public class MainFragment extends BaseFragment {
    public static final int KEY = 1;
    public static final String TAG = "MainFragment";
    public static final String PARAM_TEXT = "param_text";

    List<String> titles;
    private TextView label;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EmptyRecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;

    @Override
    public void initViews(View v) {

        label = (TextView) v.findViewById(R.id.tv_content);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.mSwipeRefreshLayout);
        mRecyclerView = (EmptyRecyclerView) v.findViewById(R.id.mRecyclerView);

        titles = new ArrayList<>();
        titles.add("国土局特工");
        titles.add("红~葡萄酒");
        titles.add("可乐の事");
        titles.add("机场专线");
        titles.add("如我家");
        titles.add("旅游项目");
        titles.add("秦始皇");
        titles.add("BoKai Wang");
        titles.add("国土局特工");
        titles.add("红~葡萄酒");
        titles.add("可乐の事");
        titles.add("机场专线");
        titles.add("如我家");
        titles.add("旅游项目");
        titles.add("秦始皇");
        titles.add("BoKai Wang");
        titles.add("国土局特工");
        titles.add("红~葡萄酒");
        titles.add("可乐の事");
        titles.add("机场专线");
        titles.add("如我家");
        titles.add("旅游项目");
        titles.add("秦始皇");
        titles.add("BoKai Wang");

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        lableText = getArguments().getString(PARAM_TEXT, "---");

        if (getContext().getString(R.string.tab_contacts).equals(lableText)) {
            adapter = new ContactsRecyclerAdapter(getContext(), titles);
            mRecyclerView.setAdapter(adapter);
/*            mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.d("setOnTouchListener", "setOnTouchListener");
                    ((HomeRecyclerAdapter) adapter).cancleExpandStatus();
                    return false;
                }
            });*/
        } else {
            adapter = new HomeRecyclerAdapter(getContext(), titles);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.d("setOnTouchListener", "setOnTouchListener");
                    ((HomeRecyclerAdapter) adapter).cancleExpandStatus();
                    return false;
                }
            });
        }
    }

    @Override
    protected View initFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    String lableText = "defult";

    @Override
    protected void inflateData() {
        if (getArguments() != null) {
            lableText = getArguments().getString(PARAM_TEXT, "---");
            label.setText(lableText);
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("ziuo", lableText + ": is visible!");
        }
    }
}
