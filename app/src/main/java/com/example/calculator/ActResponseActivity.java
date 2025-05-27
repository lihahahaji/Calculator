package com.example.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculator.utils.DateUtil;

public class ActResponseActivity extends AppCompatActivity implements View.OnClickListener {

    private static String mResponse = "我还没睡";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act_response);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv_request = findViewById(R.id.tv_request);

        // 从上一个activity中获取Bundle数据
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String request_time = bundle.getString("request_time");
            String request_content = bundle.getString("request_content");
            String desc = String.format("收到请求消息：\n 请求的时间为:%s \n 请求的内容为：%s\n",request_time,request_content);
            tv_request.setText(desc);
        }


        findViewById(R.id.btn_response).setOnClickListener(this);

        TextView tv_response = findViewById(R.id.tv_response);
        tv_response.setText("待返回的消息为："+mResponse);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("response_time", DateUtil.getNowTime());
        bundle.putString("response_content",mResponse);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        // 结束当前的活动页面
        finish();
    }
}