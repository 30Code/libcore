package cn.linhome.libcore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sunday.eventbus.SDEventManager;

import cn.linhome.libcore.R;
import cn.linhome.library.activity.SDBaseActivity;
import cn.linhome.library.utils.LogUtil;

public class MainActivity extends SDBaseActivity
{

    private Button btn_test;

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.act_main);
        btn_test = findViewById(R.id.btn_test);

        btn_test.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}