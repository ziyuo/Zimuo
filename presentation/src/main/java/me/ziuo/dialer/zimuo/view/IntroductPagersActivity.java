package me.ziuo.dialer.zimuo.view;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import me.ziuo.dialer.zimuo.R;
import me.ziuo.dialer.zimuo.view.utils.Constants;
import me.ziuo.dialer.zimuo.view.utils.Utils;

/**
 * Created by wangbokai on 2016/3/30.
 */
public class IntroductPagersActivity extends BaseActivity {

    /**
     * 声明控件
     */
    private ViewPager mViewPager;
    SectionsPagerAdapter mSectionPagerAdapter;
    ImageButton mNextBtn;
    Button mSkipBtn, mFinishBtn;
    //三个indicator图标
    ImageView zeroImgView, oneImgView, twoImgView;
    ImageView[] indicators;

    //整个最大的统筹布局
    CoordinatorLayout mCoordinator;

    int pageIndex = 0;
    int[] colorList;

    @Override
    protected void initViews() {
        mSectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mNextBtn = (ImageButton) findViewById(R.id.intro_btn_next);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
        /**
         * 低版本时，使用代码动态渲染矢量图. 兼容性考虑
         */
            mNextBtn.setImageDrawable(
                    Utils.tintMyDrawable(ContextCompat.getDrawable(this, R.drawable.ic_chevron_right_24dp), Color.WHITE)
            );
        mSkipBtn = (Button) findViewById(R.id.intro_btn_skip);
        mFinishBtn = (Button) findViewById(R.id.intro_btn_finish);

        zeroImgView = (ImageView) findViewById(R.id.intro_indicator_0);
        oneImgView = (ImageView) findViewById(R.id.intro_indicator_1);
        twoImgView = (ImageView) findViewById(R.id.intro_indicator_2);

        mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);

        indicators = new ImageView[]{zeroImgView, oneImgView, twoImgView};

        mViewPager = (ViewPager) findViewById(R.id.containter);
        mViewPager.setAdapter(mSectionPagerAdapter);

        mViewPager.setCurrentItem(pageIndex);

        colorList = new int[]{ContextCompat.getColor(this, R.color.cyan),
                ContextCompat.getColor(this, R.color.orange),
                ContextCompat.getColor(this, R.color.green)};
        updateIndicators(pageIndex);


    }

    @Override
    protected void addListeners() {
        /**
         * Argb 色值计算器，可以用来计算两个int类型Argb颜色值间的过度色值
         */
        final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /**
                 * 背景颜色更新
                 * 计算偏移量对应的过度色值
                 */
                int colorUpdate = (int) argbEvaluator.evaluate(positionOffset, colorList[position], colorList[position == colorList.length - 1 ? position : position + 1]);
                mViewPager.setBackgroundColor(colorUpdate);
            }

            @Override
            public void onPageSelected(int position) {
                pageIndex = position;
                updateIndicators(pageIndex);
                mViewPager.setBackgroundColor(colorList[position]);
                /**
                 * 继续与完成的引到状态
                 */
                mNextBtn.setVisibility(position == colorList.length - 1 ? View.GONE : View.VISIBLE);
                mFinishBtn.setVisibility(position == colorList.length - 1 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageIndex += 1;
                mViewPager.setCurrentItem(pageIndex, true);
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //  update 1st time pref
                getaCache().put(Constants.CACHE_USER_FIRST_TIME, Constants.SUCCESS);

            }
        });
    }

    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected);
        }
    }

    @Override
    protected int getContentResId() {
        //设置低版本状态栏透明的方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_trans80));
        }
        return R.layout.activity_introduct_pagers;
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        //背景数组
        int[] bgs = new int[]{R.drawable.ic_flight_24dp, R.drawable.ic_mail_24dp, R.drawable.ic_explore_24dp};

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            ImageView img = (ImageView) rootView.findViewById(R.id.section_img);
            img.setBackgroundResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
