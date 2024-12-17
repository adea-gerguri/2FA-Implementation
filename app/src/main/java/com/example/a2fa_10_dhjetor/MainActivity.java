package com.example.a2fa_10_dhjetor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_login);
            Handler handler  = new Handler();
            handler.postDelayed( new Runnable (){
                public void run(){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            },1000);

        }
}
