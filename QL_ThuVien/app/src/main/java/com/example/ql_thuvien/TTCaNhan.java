package com.example.ql_thuvien;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TTCaNhan extends AppCompatActivity {
    EditText edTaiKhoan , edMatkhau , edHoten , edGiotinh , edNgaysinh,Sodt,Email;
    Button btnCapNhat;
    String name;
    SQL db;
    int id;
    String Taikhoan;
    String MatKhau;
    String Hoten;
    String GioiTinh;
    String NgaySinh;
    String Sodt1;
    String Email1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cap_nhat_username);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        db=new SQL(this,"Qlthuvien.db",null,1);

        edTaiKhoan = (EditText)findViewById(R.id.edTaikhoantt);
        edMatkhau = (EditText)findViewById(R.id.edMatkhautt);
        edHoten = (EditText)findViewById(R.id.edHotentt);
        edGiotinh = (EditText)findViewById(R.id.edGioitinhtt);
        edNgaysinh = (EditText)findViewById(R.id.edNgaysinhtt);
        Sodt = (EditText)findViewById(R.id.edSdttt);
        Email = (EditText)findViewById(R.id.edEmailtt);
        btnCapNhat = (Button)findViewById(R.id.Capnhattt);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        getData(name);


        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Taikhoan = edTaiKhoan.getText().toString();
                    MatKhau = edMatkhau.getText().toString();
                    Hoten= edHoten.getText().toString();
                    GioiTinh = edGiotinh.getText().toString();
                    NgaySinh = edNgaysinh.getText().toString();
                    Sodt1 = Sodt.getText().toString();
                    Email1 = Email.getText().toString();
                    db.UpdateUser(id,Taikhoan,MatKhau,Hoten,GioiTinh,NgaySinh,Sodt1,Email1);
                    Toast.makeText(getApplicationContext(),"Cập Nhật Thành Công",Toast.LENGTH_LONG).show();
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Lỗi"+ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    private void getData(String name) {
        Cursor cursor = MainActivity.db.TruyVanTraVe("Select * from User where Taikhoan = '"+name+"'");
        User user = new User();
        try {
            if (cursor.moveToFirst()) {
               user.setId(cursor.getInt(0));
               user.setTaikhoan(cursor.getString(1));
               user.setMatKhau(cursor.getString(2));
               user.setHoten(cursor.getString(3));
               user.setGioiTinh(cursor.getString(4));
               user.setNgaySinh(cursor.getString(5));
               user.setSodt(cursor.getString(6));
               user.setEmail(cursor.getString(7));
            }
        } catch (Exception ex) {
           Toast.makeText(getApplication(),"Loi"+ex,Toast.LENGTH_SHORT).show();
        }
        id = user.getId();
        edTaiKhoan.setText(user.getTaikhoan());
        //edTaiKhoan.setText(String.valueOf(id));
        edMatkhau.setText(user.getMatKhau());
        edHoten.setText(user.getHoten());
        edGiotinh.setText(user.getGioiTinh());
        edNgaysinh.setText(user.getNgaySinh());
        Sodt.setText(user.getSodt());
        Email.setText(user.getEmail() );
    }
}
