package me.ziuo.dialer.zimuo.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.ziuo.dialer.zimuo.R;

/**
 * Created by wangbokai on 2016/5/11.
 */
public class HomeRecyclerAdapter extends BaseRecyclerAdapter<String, HomeRecyclerAdapter.MyRecyclerHolder> {
    public HomeRecyclerAdapter(Context context, List<String> dataRecords) {
        super(context, dataRecords);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lottery_records, viewGroup, false);
        MyRecyclerHolder viewHolder = new MyRecyclerHolder(view);
        return viewHolder;
    }

    @Override
    public void bindDataView(final MyRecyclerHolder recordsHolder, String record) {
        recordsHolder.itemView.setBackgroundResource(R.drawable.recycler_bg);
        if (!TextUtils.isEmpty(record)) {
            recordsHolder.name.setText(record);
        }
        if (recordsHolder.getLayoutPosition() == expandPos) {
            recordsHolder.expand_option.setVisibility(View.VISIBLE);
        } else {
            recordsHolder.expand_option.setVisibility(View.GONE);

        }

        // 如果设置了回调，则设置点击事件
        if (getOnItemClick() != null) {//外部处理
            recordsHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int pos = recordsHolder.getLayoutPosition();
                    getOnItemClick().onItemClick();
                }
            });

            recordsHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    int pos = recordsHolder.getLayoutPosition();
                    getOnItemClick().onItemLongClick();
                    return false;
                }
            });
        } else {
            recordsHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandPos = -1;
                    notifyDataSetChanged();
                }
            });
            recordsHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onPressed = true;
                    expandPos = recordsHolder.getLayoutPosition();
                    notifyDataSetChanged();
                    return true;
                }
            });
        }
    }

    boolean onPressed;

    public void cancleExpandStatus() {
        if (expandPos != -1 && !onPressed) {
            expandPos = -1;
            notifyDataSetChanged();
        }
        onPressed = false;
    }

    int expandPos = -1;

    class MyRecyclerHolder extends RecyclerView.ViewHolder {

        TextView name;
        View item_bg;
        View expand_option;

        public MyRecyclerHolder(View root) {
            super(root);
            initViews(root);
        }

        private void initViews(View root) {
            name = (TextView) root.findViewById(R.id.gameName);
            item_bg = root.findViewById(R.id.item_bg);
            expand_option = root.findViewById(R.id.expand_option);
        }
    }
}
