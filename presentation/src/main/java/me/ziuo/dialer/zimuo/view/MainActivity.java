package me.ziuo.dialer.zimuo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import javax.inject.Inject;

import me.ziuo.dialer.zimuo.R;
import me.ziuo.dialer.zimuo.presenter.MainPresenter;
import me.ziuo.dialer.zimuo.view.adapters.CustomPagerAdapter;
import me.ziuo.dialer.zimuo.view.utils.Constants;

public class MainActivity extends BaseActivity {

    /**
     * 声明布局中的控件
     */
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FrameLayout mContentFrame;
    View mNavigationHeader;
    ViewPager mViewPager;


    private CustomPagerAdapter mPagerAdapter;
    TabLayout tabLayout;
    @Inject
    MainPresenter mainPresenter;

    private int mCurrentSelectedPosition;

    private String isUserFirstTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivityComponent().inject(this);

    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    private void setUpNavDrawer() {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.mipmap.ic_drawer);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

    }

    @Override
    protected void initViews() {
        /**
         * 初始化并设置Toolbar
         */
        setUpToolbar();
        //查找DrawerLayout并初始化
        mDrawerLayout = (DrawerLayout) findViewById(R.id.slide_menu);
        /**
         * 开启导航抽屉
         */
        setUpNavDrawer();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mContentFrame = (FrameLayout) findViewById(R.id.nav_contentframe);
        mNavigationHeader = mNavigationView.getHeaderView(0);
        mPagerAdapter = new CustomPagerAdapter(getApplicationContext(), getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.mian_viewpager);
        setupViewPager(mViewPager);
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager mViewPager) {
        mPagerAdapter = new CustomPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "item.getTitle():" + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void addListeners() {
        /**
         * 设置条目点击事件
         */
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        Snackbar.make(mContentFrame, "智能分组", Snackbar.LENGTH_SHORT).show();
                        mCurrentSelectedPosition = 0;
                        return true;
                    case R.id.navigation_item_2:
                        Snackbar.make(mContentFrame, "关于我们", Snackbar.LENGTH_SHORT).show();
                        mCurrentSelectedPosition = 1;
                        return true;
                    default:
                        return true;
                }
            }
        });
        mNavigationHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Snackbar.make(mContentFrame, "我的详情", Snackbar.LENGTH_SHORT).show();*/
                startActivity(new Intent(MainActivity.this, AccountDetailActivity.class));
            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        showToast("One");
                        break;
                    case 1:
                        showToast("Two");

                        break;
                    case 2:
                        showToast("Three");

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected int getContentResId() {

        isUserFirstTime = getaCache().getAsString(Constants.CACHE_USER_FIRST_TIME);
        if (!Constants.SUCCESS.equals(isUserFirstTime)) {
            Intent introIntent = new Intent(MainActivity.this, IntroductPagersActivity.class);
            startActivity(introIntent);
        }

        return R.layout.activity_main;
    }

    /**
     * 生命周期方法 防止Activity销毁导致显示异常
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentSelectedPosition = savedInstanceState.getInt(Constants.STATE_SELECTED_POSITION, 0);
        Menu menu = mNavigationView.getMenu();
        menu.getItem(mCurrentSelectedPosition).setChecked(true);
    }

/*    *//**
     * @return 状态栏的高度
     *//*
    public int getStatusBarHeight() {

        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }*/
}
