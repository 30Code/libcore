package cn.linhome.library;

import android.app.Application;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.sunday.eventbus.SDEventManager;

import cn.linhome.lib.receiver.FNetworkReceiver;
import cn.linhome.lib.utils.context.FContext;
import cn.linhome.lib.utils.extend.FActivityStack;
import cn.linhome.lib.utils.extend.FAppBackgroundListener;
import cn.linhome.library.event.EAppBackground;
import cn.linhome.library.event.EAppResumeFromBackground;
import cn.linhome.library.event.ECallStateChanged;
import cn.linhome.library.event.ENetworkChanged;

public class SDLibrary
{
    private static SDLibrary sInstance;
    private Context mContext;

    private SDLibrary()
    {
    }

    public static SDLibrary getInstance()
    {
        if (sInstance == null)
        {
            synchronized (SDLibrary.class)
            {
                sInstance = new SDLibrary();
            }
        }
        return sInstance;
    }

    public Context getContext()
    {
        return mContext;
    }

    public synchronized void init(Application application)
    {
        if (mContext == null)
        {
            mContext = application;

            FContext.set(application);
            FActivityStack.getInstance().init(application);
            FAppBackgroundListener.getInstance().init(application);

            initInternal();
        }
    }

    private void initInternal()
    {
        FAppBackgroundListener.getInstance().addCallback(new FAppBackgroundListener.Callback()
        {
            @Override
            public void onBackground()
            {
                EAppBackground event = new EAppBackground();
                SDEventManager.post(event);
            }

            @Override
            public void onResumeFromBackground()
            {
                EAppResumeFromBackground event = new EAppResumeFromBackground();
                SDEventManager.post(event);
            }
        });

        new FNetworkReceiver()
        {
            @Override
            protected void onNetworkChanged(int type)
            {
                ENetworkChanged event = new ENetworkChanged();
                event.type = type;
                SDEventManager.post(event);
            }
        }.register(getContext());

        TelephonyManager tm = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener()
        {
            @Override
            public void onCallStateChanged(int state, String incomingNumber)
            {
                ECallStateChanged event = new ECallStateChanged();
                event.state = state;
                event.incomingNumber = incomingNumber;
                SDEventManager.post(event);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }

}
