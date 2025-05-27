package com.example.calculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActionUriActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_action_uri);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
        findViewById(R.id.btn_my).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int btn_id = v.getId();

        String phoneNum = "12345";
        Intent intent = new Intent();

        if(btn_id == R.id.btn_dial){

            // 创建一个拨号的 Action
            intent.setAction(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:"+phoneNum);
            intent.setData(uri);
            startActivity(intent);
        }
        else if(btn_id == R.id.btn_sms){
            //  创建一个发短信的 Action
            intent.setAction(Intent.ACTION_SENDTO);
            Uri uri2 = Uri.parse("smsto:"+phoneNum);
            intent.setData(uri2);
            startActivity(intent);
        }

    }
}