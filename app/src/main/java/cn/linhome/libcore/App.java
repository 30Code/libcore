package cn.linhome.libcore;

import cn.linhome.library.SDLibrary;
import cn.linhome.library.app.FApplication;
import cn.linhome.library.utils.LogUtil;
import de.greenrobot.event.SubscriberExceptionEvent;

/**
 * Created by Administrator on 2017/5/27.
 */

public class App extends FApplication
{

    private static App sInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        sInstance = this;
    }

    @Override
    protected void onCreateMainProcess()
    {

    }

    public static App getInstance()
    {
        return sInstance;
    }

    public void onEventMainThread(SubscriberExceptionEvent event)
    {
        LogUtil.i("------------ test >> SubscriberExceptionEvent");
    }
}
