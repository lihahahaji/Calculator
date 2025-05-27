package com.example.calculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActReciveActivity extends AppCompatActivity {

    private TextView tv_receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act_recive);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_receive = findViewById(R.id.tv_receive);

        // 从上一个activity中获取Bundle数据
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String request_time = bundle.getString("request_time");
            String request_content = bundle.getString("request_content");
            String desc = String.format("收到请求消息：\n 请求的时间为:%s \n 请求的内容为：%s\n",request_time,request_content);
            tv_receive.setText(desc);
        }


    }
}