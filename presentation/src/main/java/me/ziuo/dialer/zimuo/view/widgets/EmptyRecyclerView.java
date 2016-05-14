package me.ziuo.dialer.zimuo.view.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangbokai on 2016/4/17.
 * 具有数据为空时显示空布局的能力、
 * 实例代码
 * {
 * final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
 * recyclerView.setLayoutManager(linearLayoutManager);
 * recyclerView.setHasFixedSize(true);
 * recyclerView.setEmptyView(view.findViewById(R.id.empty_msg));
 * <p/>
 * }
 */
public class EmptyRecyclerView extends RecyclerView {

    private View mEmptyView;

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }
        emptyObserver.onChanged();
        //一定要调用一下，通知观察者显示空View
    }

    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            //数据改变时回调
            Adapter<?> adapter = getAdapter();
            if (adapter != null && mEmptyView != null) {
                //如果没数据
                if (adapter.getItemCount() == 0) {
                    //显示mEmptyView，隐藏自身
                    mEmptyView.setVisibility(View.VISIBLE);
                    EmptyRecyclerView.this.setVisibility(View.GONE);
                } else {
                    //显示自身，隐藏mEmptyView
                    mEmptyView.setVisibility(View.GONE);
                    EmptyRecyclerView.this.setVisibility(View.VISIBLE);
                }
            }
        }
    };
}
