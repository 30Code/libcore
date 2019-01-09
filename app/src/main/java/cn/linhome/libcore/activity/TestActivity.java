package cn.linhome.libcore.activity;

import android.os.Bundle;

import cn.linhome.libcore.R;
import qiu.niorgai.StatusBarCompat;

/**
 * des:
 * Created by 30Code
 * on 2019/1/2
 */
public class TestActivity extends BaseActivity
{

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.act_test);
    }

    @Override
    protected void setStatusBar()
    {
        super.setStatusBar();
        StatusBarCompat.translucentStatusBar(this);
    }
}
