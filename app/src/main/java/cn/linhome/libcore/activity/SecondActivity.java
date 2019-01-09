package cn.linhome.libcore.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import cn.linhome.lib.title.FTitle;
import cn.linhome.lib.utils.context.FResUtil;
import cn.linhome.libcore.R;
import qiu.niorgai.StatusBarCompat;

public class SecondActivity extends BaseActivity
{
    private FTitle mTitleView;
    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.act_second);
        mTitleView = findViewById(R.id.title);
        mTitleView.getItemLeft().setImageLeft(R.mipmap.ic_arrow_back);
        mTitleView.addItemMiddle().setTextTop("测试");

        FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) mTitleView.getLayoutParams();
        layoutParams.topMargin = FResUtil.getStatusBarHeight();
        mTitleView.setLayoutParams(layoutParams);
    }

    @Override
    protected void setStatusBar()
    {
        super.setStatusBar();
        StatusBarCompat.translucentStatusBar(this);
    }
}