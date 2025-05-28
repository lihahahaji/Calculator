package com.example.calculator;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MetaDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meta_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv_meta = findViewById(R.id.tv_meta);

        // 在代码中获取元数据
        // 调用getPackageManager方法获得当前应用的包管理器
        PackageManager pm = getPackageManager();
        try {
            // 调用包管理器的getActivityInfo方法获得当前活动的信息对象；
            ActivityInfo info = pm.getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);

            // 获取 activity 附加的元数据信息
            Bundle metaData = info.metaData;

            String weather = metaData.getString("weather");

            tv_meta.setText(weather);


        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }





        // 活动信息对象的metaData是Bundle包裹类型，调用包裹对象的getString即可获得指定名称的参数值；


    }
}