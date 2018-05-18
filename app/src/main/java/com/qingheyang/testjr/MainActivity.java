package com.qingheyang.testjr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        button = findViewById(R.id.main_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getUser(HttpUtils.URL);//地址要自己更换
            }
        });
    }

    /**
     * 成功的回调
     * @param user
     */
    public void getDateSuccess(User user){
        //doSomethings...
    }

    /**
     * 失败的回调
     * @param code
     */
    public void getDateFailed(int code){
        switch (code){
            case 0x001:
                Toast.makeText(this, "获取信息失败", Toast.LENGTH_SHORT).show();
                break;
            case 0x002:
                break;
        }
    }
}
