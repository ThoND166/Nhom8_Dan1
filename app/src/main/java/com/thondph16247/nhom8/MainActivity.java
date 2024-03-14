package com.thondph16247.nhom8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.thondph16247.nhom8.Activitys.DangNhapActivity;
import com.thondph16247.nhom8.Fragments.DoanhThu_Frag;
import com.thondph16247.nhom8.Fragments.GioHang_Frag;
import com.thondph16247.nhom8.Fragments.HoaDon_Frag;
import com.thondph16247.nhom8.Fragments.KhachHang_Frag;
import com.thondph16247.nhom8.Fragments.LoaiTraiCay_Frag;
import com.thondph16247.nhom8.Fragments.NhanVien_Frag;
import com.thondph16247.nhom8.Fragments.SanPham_Frag;
import com.thondph16247.nhom8.R;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout001;
    Toolbar mtoolbar001;
    NavigationView nav001;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout001 = findViewById(R.id.drawerlayout001);
        mtoolbar001 = findViewById(R.id.mtoolbar001);
        nav001 = findViewById(R.id.nav001);
        context = this;
        NhanVien_Frag fr = new NhanVien_Frag();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container001, fr)
                .commit();
        mtoolbar001.setTitle("Danh sách nhân viên");
        setSupportActionBar( mtoolbar001);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout001,
                mtoolbar001,
                R.string.open,
                R.string.close // vào file values/string thêm 2 thẻ string đặt tên open và close
                //          <string name="open">Open</string>
                //           <string name="close">Close</string>
        );
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout001.addDrawerListener(drawerToggle);
        nav001.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fr ;
                if(item.getItemId() == R.id.nav_nhanVien){
                    // trang chính thì hiện frag111
                    fr = new NhanVien_Frag();
                    mtoolbar001.setTitle("Danh sách nhân viên");
                }else  if(item.getItemId() ==R.id.nav_loaiTraiCay ){

                    fr = new LoaiTraiCay_Frag();
                    mtoolbar001.setTitle("Loại trái cây");
                }
                else  if(item.getItemId() ==R.id.nav_sanPham ){
                    fr = new SanPham_Frag();
                    mtoolbar001.setTitle("Danh sách sản phẩm");
                }
                else  if(item.getItemId() ==R.id.nav_khachHang ){
                    fr = new KhachHang_Frag();
                    mtoolbar001.setTitle("Khách hàng");
                }
                else  if(item.getItemId() ==R.id.nav_hoaDon ){
                    fr = new HoaDon_Frag();
                    mtoolbar001.setTitle("Hóa đơn");
                }
                else  if(item.getItemId() ==R.id.nav_gioHang ){
                    fr = new GioHang_Frag();
                    mtoolbar001.setTitle("Giỏ hàng");
                }
                else  if(item.getItemId() ==R.id.nav_doanhThu ){
                    fr = new DoanhThu_Frag();
                    mtoolbar001.setTitle("Doanh Thu");
                }
                else  if(item.getItemId() ==R.id.nav_dangXuat ){
                    DangXuat();
                    return true;
                }
                else  if(item.getItemId() ==R.id.nav_doiMK ){
                    ShowDialog();
                    return true;
                }

                else{
                    fr = new NhanVien_Frag(); // để tạm bằng frag1
                    mtoolbar001.setTitle( item.getTitle()  );
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container001, fr)
                        .commit();
                drawerLayout001.close();
                return true;
            }
            private void DangXuat() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có muốn thoát?").setTitle("CẢNH BÁO !!!!!")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(MainActivity.this, DangNhapActivity.class));
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            private void ShowDialog() {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_doipass);

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }
}