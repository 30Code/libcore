package cn.linhome.libcore.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sunday.eventbus.SDBaseEvent;
import com.sunday.eventbus.SDEventManager;

import cn.linhome.libcore.R;
import cn.linhome.library.activity.SDBaseActivity;
import cn.linhome.library.utils.LogUtil;

public class SecondActivity extends SDBaseActivity
{

    private Button btn_test;

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.act_main);
        btn_test = findViewById(R.id.btn_test);
        btn_test.setVisibility(View.GONE);
    }

    public void onEventMainThread(ETestModel event)
    {
        LogUtil.i("------------ test >> 收到");
    }
}