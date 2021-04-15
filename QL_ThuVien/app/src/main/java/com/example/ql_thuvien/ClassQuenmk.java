package com.example.ql_thuvien;

import android.Manifest;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ClassQuenmk extends AppCompatActivity {
    EditText edTaiKhoan , edMk;
    Button btnDongy;
    SQL db;
    String taikhoan , matkhau;
    SQLiteDatabase dp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quenmk);
        edTaiKhoan = (EditText)findViewById(R.id.edTaikhoanqmk);
        edMk = (EditText)findViewById(R.id.edmkquenmk);
        btnDongy = (Button)findViewById(R.id.Doimk);
        db=new SQL(this,"Qlthuvien.db",null,1);
        btnDongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taikhoan = edTaiKhoan.getText().toString();
                matkhau = edMk.getText().toString();
                if (isUser(taikhoan)){
                    db.updatMatkhau(taikhoan,matkhau);
                    Toast.makeText(getApplication(),"Cập nhật thành công ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplication(),"Tai khoan khong ton tai",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean isUser(String name ){
        try{
            dp = openOrCreateDatabase(MainActivity.DataBasename,MODE_PRIVATE,null);
            Cursor c = dp.rawQuery("select *from User where Taikhoan = ? ",new String[]{name});
            c.moveToFirst();
            if(c.getCount()>0){
                return true;
            }
        }catch (Exception exception){
            Toast.makeText(this,"Lỗi hệ thống ",Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
