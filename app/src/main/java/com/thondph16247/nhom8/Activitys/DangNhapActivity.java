package com.thondph16247.nhom8.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.thondph16247.nhom8.DAO.DangKyDAO;
import com.thondph16247.nhom8.MainActivity;
import com.thondph16247.nhom8.R;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangnhap, btnDangKy;
    TextInputEditText edt_TenDn, edt_matKhau, edt_gmail;
    CheckBox chkLuuMK;

    Spinner spn_quyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        btnDangnhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btn_Dangky);
        edt_matKhau = findViewById(R.id.edt_MatKhau);
        edt_TenDn = findViewById(R.id.edt_TenDangNhap);
        edt_gmail = findViewById(R.id.edt_Gmail);
        chkLuuMK = findViewById(R.id.chk_luumk);
        spn_quyen = findViewById(R.id.spn_quyen);

        String[] quyen = new String[]{"Admin", "Người dùng"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quyen);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_quyen.setAdapter(adapter);

        loadLoginInfo();

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDN = edt_TenDn.getText().toString();
                String mk = edt_matKhau.getText().toString();
                String gmail = edt_gmail.getText().toString();
                DangKyDAO dao = new DangKyDAO(getApplicationContext());
                boolean loggedIn = dao.checkLogin(tenDN, mk, gmail);

                String selectedRole = spn_quyen.getSelectedItem().toString();
                boolean isAdmin = "Admin".equals(selectedRole);

                if (chkLuuMK.isChecked()) {
                    saveLoginInfo(tenDN, mk, gmail);
                    saveLoginInfo(isAdmin);
                } else {
                    clearLoginInfo();
                }

                if ((isAdmin && "admin".equalsIgnoreCase(tenDN) && "admin".equalsIgnoreCase(mk)) || (!isAdmin && loggedIn)) {
                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });
    }

    private void loadLoginInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String tenDN = sharedPreferences.getString("tenDN", "");
        String matKhau = sharedPreferences.getString("matKhau", "");
        String gmail = sharedPreferences.getString("gmail", "");
        boolean isRemembered = sharedPreferences.getBoolean("isRemembered", false);

        edt_TenDn.setText(tenDN);
        edt_matKhau.setText(matKhau);
        edt_gmail.setText(gmail);
        chkLuuMK.setChecked(isRemembered);
    }

    private void saveLoginInfo(String tenDN, String matKhau, String gmail) {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tenDN", tenDN);
        editor.putString("matKhau", matKhau);
        editor.putString("gmail", gmail);
        editor.putBoolean("isRemembered", true);
        editor.apply();
    }

    private void clearLoginInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("tenDN");
        editor.remove("matKhau");
        editor.remove("gmail");
        editor.remove("isRemembered");
        editor.apply();
    }

    private void saveLoginInfo(boolean isAdmin) {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isAdmin", isAdmin);
        editor.apply();
    }
}
