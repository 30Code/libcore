package cn.linhome.library.app;

import android.app.Application;
import cn.linhome.lib.utils.FOtherUtil;
import cn.linhome.library.SDLibrary;

public abstract class FApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        final String processName = FOtherUtil.getProcessName(this);
        if (getPackageName().equals(processName))
        {
            SDLibrary.getInstance().init(this);
            onCreateMainProcess();
        }
    }

    /**
     * 主进程调用
     */
    protected abstract void onCreateMainProcess();
}
