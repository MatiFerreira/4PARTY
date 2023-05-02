package com.example.a4party.SplashScreen;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a4party.IntefaceAPP.HOME_ACTIVITY;
import com.example.a4party.LogInOutUser.Login_activity;
import com.example.a4party.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login_activity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}


