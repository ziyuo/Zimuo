package me.ziuo.dialer.zimuo.view.adapters;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.ziuo.dialer.zimuo.R;

/**
 * Created by wangbokai on 2016/4/14.
 */
public abstract class BaseRecyclerAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView

    List<T> dataRecords;

    Context context;

    public List<T> getLotteryRecords() {
        return dataRecords;
    }

    public void setDataRecords(List<T> dataRecords) {
        this.dataRecords = dataRecords;
    }

    public void addLotteryRecords(List<T> dataRecords) {
        if (dataRecords != null && dataRecords.size() > 0)
            this.dataRecords.addAll(dataRecords);
    }


    public BaseRecyclerAdapter(Context context, List<T> dataRecords) {
        this.setDataRecords(dataRecords);
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            return getViewHolder(viewGroup);
        } else if (viewType == TYPE_FOOTER) {
            View foot_view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_footer_view, viewGroup, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            FootViewHolder footViewHolder = new FootViewHolder(foot_view);
            return footViewHolder;
        }
        return null;
    }

    @NonNull
    public abstract RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup);


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int index) {
        if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            if (getLotteryRecords() != null && getLotteryRecords().size() / 20 > 0 && getLotteryRecords().size() % 20 == 0) {//满一页
                switch (load_more_status) {
                    case PULLUP_LOAD_MORE:
                        footViewHolder.loading_logo.setVisibility(View.GONE);
                        footViewHolder.foot_view_item_tv.setVisibility(View.VISIBLE);
                        footViewHolder.foot_view_item_tv.setText("上拉加载更多...");
                        break;
                    case LOADING_MORE:
                        footViewHolder.foot_view_item_tv.setText("正在加载更多数据...");
                        footViewHolder.foot_view_item_tv.setVisibility(View.GONE);
                        footViewHolder.loading_logo.setVisibility(View.VISIBLE);
                        AnimationDrawable ami = (AnimationDrawable) footViewHolder.loading_logo.getBackground();
                        ami.start();
                        break;
                }
            } else {
                footViewHolder.loading_logo.setVisibility(View.GONE);
                footViewHolder.foot_view_item_tv.setVisibility(View.VISIBLE);
                footViewHolder.foot_view_item_tv.setText("到底了...");

            }
        } else {

            bindDataView((H) holder, dataRecords.get(index));

        }
    }

    public abstract void bindDataView(H recordsHolder, T record);

    @Override
    public int getItemCount() {
        if (getLotteryRecords() == null) {
            return 0;
        }

        int itemCount = getLotteryRecords().size() < 10 ? getLotteryRecords().size() : (getLotteryRecords().size() + 1);
        return itemCount;
    }

    /**
     * 进行判断是普通Item视图还是FootView视图
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (getLotteryRecords().size() >= 10 && position == getLotteryRecords().size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * //上拉加载更多
     * PULLUP_LOAD_MORE=0;
     * //正在加载中
     * LOADING_MORE=1;
     * //加载完成已经没有更多数据了
     * NO_MORE_DATA=2;
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends RecyclerView.ViewHolder {
        public TextView foot_view_item_tv;
        public ImageView loading_logo;

        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv = (TextView) view.findViewById(R.id.refresh_msg);
            loading_logo = (ImageView) view.findViewById(R.id.loading_logo);
        }
    }

    OnItemClickListener mItemClickListener;

    public OnItemClickListener getOnItemClick() {
        return mItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick();

        void onItemLongClick();
    }
}
