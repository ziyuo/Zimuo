package me.ziuo.dialer.zimuo.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import me.ziuo.dialer.zimuo.internal.di.scopes.PerActivity;
import me.ziuo.dialer.zimuo.internal.di.test.QvodPlayer;
import me.ziuo.dialer.zimuo.internal.di.test.trees.CaffeMaker;
import me.ziuo.dialer.zimuo.view.MainActivity;

/**
 * Created by wangbokai on 2016/3/6.
 */
@PerActivity
public class MainPresenter implements Presenter {


    /**@Inject
    QvodPlayer qvodPlayer;

    @Inject
    CaffeMaker caffeMaker;
*/
    /**
     * 为了试验Dagger的特性故意这样写的
     * 按照MVP的风格，MainActivity 应该使用接口代替
     */
    private MainActivity mainView;

    @Inject
    public MainPresenter(){

    }




    /**
     * Presenter应该持有View
     * @param mainView
     */
    public void setMainView(@NonNull MainActivity mainView) {
        this.mainView = mainView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {

    }
}
