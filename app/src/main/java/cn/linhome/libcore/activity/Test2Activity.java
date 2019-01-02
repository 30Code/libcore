package cn.linhome.libcore.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.linhome.libcore.R;
import cn.linhome.library.activity.SDBaseActivity;
import qiu.niorgai.StatusBarCompat;

/**
 * des:
 * Created by 30Code
 * on 2019/1/2
 */
public class Test2Activity extends SDBaseActivity
{
    private ImageView iv;
    private RelativeLayout rl_test;

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.act_test2);
    }

    @Override
    protected void setStatusBar()
    {
        super.setStatusBar();
        StatusBarCompat.translucentStatusBar(this);
    }
}
