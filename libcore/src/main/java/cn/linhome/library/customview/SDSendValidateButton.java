package cn.linhome.library.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import cn.linhome.lib.utils.FViewUtil;
import cn.linhome.lib.utils.context.FResUtil;
import cn.linhome.library.R;
import cn.linhome.library.utils.SDTimer;
import cn.linhome.library.utils.SDTimer.SDTimerListener;

@Deprecated
public class SDSendValidateButton extends AppCompatButton
{

    private static final int DISABLE_TIME = 60;

    private SDTimer mTimer = new SDTimer();

    private int mDisableTime = DISABLE_TIME; // 倒计时时间，默认60秒
    private int mTextColorEnable = Color.WHITE;
    private int mTextColorDisable = Color.WHITE;

    private String mTextEnable = "获取验证码";
    private String mTextDisable = "秒后重发验证码";

    private int mBackgroundEnableResId = 0;
    private int mBackgroundDisableResId = 0;

    private int mDisableTimeTemp = 0;

    private boolean mClickAble = true;

    private SDSendValidateButtonListener mListener = null;

    // ----------------get set

    public int getmDisableTime()
    {
        return mDisableTime;
    }

    public void setmDisableTime(int mDisableTime)
    {
        this.mDisableTime = mDisableTime;
    }

    public int getCurrentDisableTime()
    {
        return mDisableTimeTemp;
    }

    public int getmTextColorEnable()
    {
        return mTextColorEnable;
    }

    public void setmTextColorEnable(int mTextColorEnable)
    {
        this.mTextColorEnable = mTextColorEnable;
    }

    public int getmTextColorDisable()
    {
        return mTextColorDisable;
    }

    public void setmTextColorDisable(int mTextColorDisable)
    {
        this.mTextColorDisable = mTextColorDisable;
    }

    public String getmTextEnable()
    {
        return mTextEnable;
    }

    public void setmTextEnable(String mTextEnable)
    {
        this.mTextEnable = mTextEnable;
    }

    public String getmTextDisable()
    {
        return mTextDisable;
    }

    public void setmTextDisable(String mTextDisable)
    {
        this.mTextDisable = mTextDisable;
    }

    public int getmBackgroundEnableResId()
    {
        return mBackgroundEnableResId;
    }

    public void setmBackgroundEnableResId(int mBackgroundEnableResId)
    {
        this.mBackgroundEnableResId = mBackgroundEnableResId;
    }

    public int getmBackgroundDisableResId()
    {
        return mBackgroundDisableResId;
    }

    public void setmBackgroundDisableResId(int mBackgroundDisableResId)
    {
        this.mBackgroundDisableResId = mBackgroundDisableResId;
    }

    public SDSendValidateButtonListener getmListener()
    {
        return mListener;
    }

    public void setmListener(SDSendValidateButtonListener mListener)
    {
        this.mListener = mListener;
    }

    public SDSendValidateButton(Context context)
    {
        this(context, null);
    }

    public SDSendValidateButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView();
    }

    private void initView()
    {
        mTextColorEnable = getResources().getColor(R.color.res_main_color);
        mTextColorDisable = mTextColorEnable;

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.TRANSPARENT);
        gradientDrawable.setStroke(FResUtil.dp2px(1), mTextColorEnable);
        gradientDrawable.setCornerRadius(FResUtil.dp2px(5));

        FViewUtil.setBackgroundDrawable(this, gradientDrawable);

        updateViewState(true);
        this.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if (mListener != null && mClickAble)
                {
                    mListener.onClickSendValidateButton();
                }
            }
        });
    }

    public void startTickWork()
    {
        stopTickWork();
        if (mClickAble)
        {
            mClickAble = false;
            updateViewState(false);
            mDisableTimeTemp = mDisableTime;
            mTimer.startWork(0, 1000, new SDTimerListener()
            {

                @Override
                public void onWorkMain()
                {
                    tickWork();
                }

                @Override
                public void onWork()
                {
                }
            });
        }
    }

    public void updateViewState(boolean enable)
    {
        if (enable)
        {
            this.setText(mTextEnable);
            if (mTextColorEnable != 0)
            {
                this.setTextColor(mTextColorEnable);
            }
            if (mBackgroundEnableResId != 0)
            {
                FViewUtil.setBackgroundResource(this, mBackgroundEnableResId);
            }
        } else
        {
            this.setText(mDisableTimeTemp + mTextDisable);
            if (mTextColorDisable != 0)
            {
                this.setTextColor(mTextColorDisable);
            }
            if (mBackgroundDisableResId != 0)
            {
                FViewUtil.setBackgroundResource(this, mBackgroundDisableResId);
            }
        }
    }

    /**
     * 每秒钟调用一次
     */
    private void tickWork()
    {
        mDisableTimeTemp--;
        this.setText(mDisableTimeTemp + mTextDisable);
        if (mListener != null)
        {
            mListener.onTick();
        }
        if (mDisableTimeTemp < 0)
        {
            stopTickWork();
        }
    }

    public void stopTickWork()
    {
        mTimer.stopWork();
        mDisableTimeTemp = mDisableTime;
        updateViewState(true);
        mClickAble = true;
    }

    public interface SDSendValidateButtonListener
    {
        public void onClickSendValidateButton();

        public void onTick();
    }

}
