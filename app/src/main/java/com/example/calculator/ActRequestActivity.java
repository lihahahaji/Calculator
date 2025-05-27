package com.example.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculator.utils.DateUtil;

public class ActRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_request;
    private String mRequest = "你睡了吗";
    private ActivityResultLauncher<Intent> register;
    private TextView tv_respons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act_request);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        tv_request = findViewById(R.id.tv_request);
        tv_request.setText(mRequest);

        tv_respons = findViewById(R.id.tv_response);


        findViewById(R.id.btn_request).setOnClickListener(this);


        // 注册一个 Activity 结果监听器，当通过 register.launch(intent) 启动另一个 Activity 并返回时，回调 onActivityResult 方法处理返回的数据。
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

            Log.d("HAJI", "onActivityResult: "+result);
            if(result!=null){
                Intent intent = result.getData();
                if(intent != null && result.getResultCode() == Activity.RESULT_OK){

                    Bundle bundle = getIntent().getExtras();
                    if(bundle!=null)
                    {
                        String response_time = bundle.getString("response_time");
                        String response_content = bundle.getString("response_content");
                        String desc = String.format("收到返回消息：\n 应答的时间为:%s \n 应答的内容为：%s\n",response_time,response_content);
                        // 把返回的消息显示在文本视图上
                        tv_respons.setText(desc);
                        Log.d("HAJI", "onActivityResult: "+desc);
                    }

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,ActResponseActivity.class);
        // 创建一个Bundle来存放要传到跳转Activity的数据
        Bundle bundle = new Bundle();
        bundle.putString("request_time", DateUtil.getNowTime());
        bundle.putString("request_content",mRequest);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}