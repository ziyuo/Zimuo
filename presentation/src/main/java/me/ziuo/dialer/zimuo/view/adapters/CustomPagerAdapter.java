package me.ziuo.dialer.zimuo.view.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import me.ziuo.dialer.zimuo.R;
import me.ziuo.dialer.zimuo.view.fragments.MainFragment;

/**
 * Created by wangbokai on 2016/3/18.
 * 有别的问题可以参考这个文章:
 * http://www.cnblogs.com/lianghui66/p/3607091.html
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {
    private static final int COUNT = 3;

    private Fragment[] mFragments;
    private String[] pageTitles;
    private Context mContext;

    public CustomPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
        this.mFragments = new Fragment[COUNT];
        pageTitles = new String[]{
                mContext.getString(R.string.tab_contacts),
                mContext.getString(R.string.tab_calllog),
                mContext.getString(R.string.tab_mine)
        };

    }

    @Override
    public Fragment getItem(int position) {
        String type;
        switch (position) {
            case 0:
                type = mContext.getString(R.string.tab_contacts);
                break;
            case 1:
                type = mContext.getString(R.string.tab_calllog);
                break;
            case 2:
                type = mContext.getString(R.string.tab_mine);
                break;
            default:
                type = "";
        }
        Bundle bundle = new Bundle();
        bundle.putString(MainFragment.PARAM_TEXT, type);
        return Fragment.instantiate(mContext, MainFragment.class.getName(), bundle);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragments[position] = fragment;
        return fragment;
    }

    public Fragment[] getFragments() {
        return mFragments;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

}

