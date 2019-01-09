package cn.linhome.libcore.activity;

import android.view.View;

import cn.linhome.lib.title.FTitle;
import cn.linhome.lib.title.FTitleItem;
import cn.linhome.libcore.R;

public class BaseTitleActivity extends BaseActivity implements FTitle.Callback
{
    private FTitle mTitleView;

    @Override
    protected int onCreateTitleViewResId()
    {
        return R.layout.include_title_simple;
    }

    @Override
    protected void onInitTitleView(View view)
    {
        super.onInitTitleView(view);
        mTitleView = view.findViewById(R.id.title);
        mTitleView.setCallback(this);
        mTitleView.getItemLeft().setImageLeft(R.mipmap.ic_arrow_back);
    }

    public final FTitle getTitleView()
    {
        return mTitleView;
    }

    @Override
    public void onClickItemLeftTitleBar(int index, FTitleItem item)
    {
        finish();
    }

    @Override
    public void onClickItemMiddleTitleBar(int index, FTitleItem item)
    {

    }

    @Override
    public void onClickItemRightTitleBar(int index, FTitleItem item)
    {

    }
}
