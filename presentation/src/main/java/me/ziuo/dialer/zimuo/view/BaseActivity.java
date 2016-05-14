package me.ziuo.dialer.zimuo.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import me.ziuo.dialer.zimuo.AndroidApplication;
import me.ziuo.dialer.zimuo.internal.di.components.ActivityComponent;
import me.ziuo.dialer.zimuo.internal.di.components.ApplicationComponent;
import me.ziuo.dialer.zimuo.internal.di.components.DaggerActivityComponent;
import me.ziuo.dialer.zimuo.internal.di.modules.ActivityModule;
import me.ziuo.dialer.zimuo.view.utils.ACache;

/**
 * Created by wangbokai on 2016/3/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    ACache aCache;

    public ACache getaCache() {
        return aCache;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("wbk", " Current Class Name is " + getClass().getSimpleName());
        aCache = ACache.get(this);
        setContentView(getContentResId());
        initViews();
        addListeners();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract void initViews();

    protected abstract void addListeners();

    protected abstract int getContentResId();

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
