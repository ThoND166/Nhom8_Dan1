package com.thondph16247.nhom8.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thondph16247.nhom8.MainActivity;
import com.thondph16247.nhom8.R;


public class DangNhapActivity extends AppCompatActivity {
    Button btnDangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btnDangnhap = findViewById(R.id.btnDangNhap);
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
            }
        });
    }
}