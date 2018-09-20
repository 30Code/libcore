package cn.linhome.libcore.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.linhome.libcore.R;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }
}