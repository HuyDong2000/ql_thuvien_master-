package com.example.ql_thuvien;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClassDangKy extends AppCompatActivity {
    SQLiteDatabase dp;
    Button btnDangky;
    EditText edTaiKhoan , edMatKhau , edHoTen,edGioiTinh , edNgaySinh,edSdt,edEmail;
    SQL db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        btnDangky = (Button)findViewById(R.id.Dangky1);
        edTaiKhoan = (EditText)findViewById(R.id.edTaikhoan1);
        edMatKhau = (EditText)findViewById(R.id.edMatkhau1);
        edHoTen = (EditText)findViewById(R.id.edHoten1);
        edGioiTinh = (EditText)findViewById(R.id.edGioitinh);
        edNgaySinh = (EditText)findViewById(R.id.edNgaysinh1);
        edEmail = (EditText)findViewById(R.id.edEmail);
        edSdt = (EditText)findViewById(R.id.edSdt1);
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              initdata();
            }
        });
    }
    public void initdata(){

        dp = openOrCreateDatabase(MainActivity.DataBasename,MODE_PRIVATE,null);
        String sql;
        try{
            sql = "insert into User (Taikhoan,MatKhau,Hoten,GioiTinh,NgaySinh,Sodt,Email) values('"+edTaiKhoan.getText().toString()+"','"+edMatKhau.getText().toString()+"'," +
                    "'"+edHoTen.getText().toString()+"','"+edGioiTinh.getText().toString()+"','"+edNgaySinh.getText().toString()+"', '"+edSdt.getText().toString()+"','"+edEmail.getText().toString()+"')";
            dp.execSQL(sql);
            Toast.makeText(this,"Đăng ký thành công ",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ClassDangKy.this,MainActivity.class);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(this,"Khởi tạo giữ liệu thất bại "+ ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
