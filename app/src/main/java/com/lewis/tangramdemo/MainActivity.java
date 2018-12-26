package com.lewis.tangramdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lewis.tangramdemo.lesson1.L1Activity;
import com.lewis.tangramdemo.lesson1_click.L1Activity_click;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick({R.id.btn_l1, R.id.btn_l1_click})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_l1:
                startActivity(L1Activity.class);
                break;
            case R.id.btn_l1_click:
                startActivity(L1Activity_click.class);
                break;
            default:
                ;
        }
    }

    private void startActivity(Class<? extends CommonActivity> activityClass) {
        Intent i = new Intent(this, activityClass);
        startActivity(i);
    }

}
