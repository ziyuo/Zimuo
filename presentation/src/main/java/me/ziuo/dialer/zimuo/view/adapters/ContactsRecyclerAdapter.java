package me.ziuo.dialer.zimuo.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

import me.ziuo.dialer.zimuo.R;

/**
 * Created by wangbokai on 2016/5/13.
 */
public class ContactsRecyclerAdapter extends BaseRecyclerAdapter<String, ContactsVH> {
    String letter;
    ColorGenerator generator = ColorGenerator.MATERIAL;


    public ContactsRecyclerAdapter(Context context, List<String> dataRecords) {
        super(context, dataRecords);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contacts_list_item, viewGroup, false);
        ContactsVH viewHolder = new ContactsVH(view);
        return viewHolder;
    }

    @Override
    public void bindDataView(final ContactsVH contactsVH, String name) {
        contactsVH.name.setText(name);
        letter = String.valueOf(name.charAt(0));
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter, generator.getRandomColor());
        contactsVH.letter.setImageDrawable(drawable);//设置材料化Logo
        if (contactsVH.getLayoutPosition() == expandPos) {
            contactsVH.expand_option.setVisibility(View.VISIBLE);
        } else {
            contactsVH.expand_option.setVisibility(View.GONE);
        }
        contactsVH.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contactsVH.expand_option.getVisibility() != View.VISIBLE) {
                    expandPos = contactsVH.getLayoutPosition();
                    notifyDataSetChanged();
                } else {
                    cancleExpandStatus();//取消展开
                }
            }
        });
/*        // 如果设置了回调，则设置点击事件
        if (getOnItemClick() != null) {//外部处理
            contactsVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    int pos = recordsHolder.getLayoutPosition();
                    getOnItemClick().onItemClick();
                }
            });

            contactsVH.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    int pos = recordsHolder.getLayoutPosition();
                    getOnItemClick().onItemLongClick();
                    return false;
                }
            });
        } else {
            contactsVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandPos = -1;
                    notifyDataSetChanged();
                }
            });
            contactsVH.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onPressed = true;
                    expandPos = contactsVH.getLayoutPosition();
                    notifyDataSetChanged();
                    return true;
                }
            });
        }*/
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
}

class ContactsVH extends RecyclerView.ViewHolder {

    TextView name;
    ImageView letter;
    View root;
    ImageView more;
    ViewStub expand_option;

    public ContactsVH(View itemView) {
        super(itemView);
        this.root = itemView;
        initViews();
    }

    private void initViews() {
        name = (TextView) root.findViewById(R.id.contact_name);
        letter = (ImageView) root.findViewById(R.id.contact_letter);
        more = (ImageView) root.findViewById(R.id.more_option);
        expand_option = (ViewStub) root.findViewById(R.id.stub_expand_option);
    }
}
