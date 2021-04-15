package com.example.ql_thuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    public static SQL db;
    SQLiteDatabase dp;
    public static final String DataBasename = "Qlthuvien.db";
    Button btnDangnhap , btnDangky;
    EditText edTaikhoan , edMatkhau;
    TextView txtQuenMK;
    ImageView anh , Avata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new SQL(this,"Qlthuvien.db",null,1);
        widget();
        initdb();
        //initdata();
        dp = openOrCreateDatabase(DataBasename,MODE_PRIVATE,null);

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edTaikhoan.getText().toString();
                String mk = edMatkhau.getText().toString();
                if(isUser(edTaikhoan.getText().toString(),edMatkhau.getText().toString())){
                    Toast.makeText(getApplication(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,Homeapp2.class);
                    intent.putExtra("name",ten);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplication(),"Tai khoan khong ton tai",Toast.LENGTH_LONG).show();
                }
            }
        });
        txtQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ClassQuenmk.class);
                startActivity(intent);
            }
        });
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ClassDangKy.class);
                startActivity(intent);
            }
        });
    }
    public void widget(){
        btnDangnhap = (Button)findViewById(R.id.Dangnhap);
        btnDangky = (Button)findViewById(R.id.Dangky);
        edTaikhoan = (EditText)findViewById(R.id.Name_login);
        edMatkhau = (EditText)findViewById(R.id.Passwwork);
        txtQuenMK = (TextView) findViewById(R.id.Quenmk);
        Avata = (ImageView) findViewById(R.id.avata);
    }
    public void initdb()
    {
        db=new SQL(this,DataBasename,null,1);
        db.TruyVanKhongTraVe("Create Table If not Exists Sach(ID Integer Primary Key Autoincrement, MaSach Integer,  TenSach Text, Maloai Integer,Mota Text,Hinhanh Blob , LuonMuon Integer)");
        db.TruyVanKhongTraVe("Create Table If not Exists User(ID Integer Primary Key Autoincrement,Taikhoan Text not null , MatKhau Text , Hoten Text, GioiTinh Text , NgaySinh  Text ,Sodt Text ,Email Text)");
        //db.TruyVanKhongTraVe("Create Table If not Exists TTSachMuon(idsachmuon Integer Primary Key Autoincrement,Id_Sach Text not null, Id_user Text");
        //db.TruyVanKhongTraVe("Create Table If not Exists LoaiSach(idloai Integer Primary Key Autoincrement,TenLoai Text not null");
    }
    public void initdata(){

        dp = openOrCreateDatabase(DataBasename,MODE_PRIVATE,null);
        String sql;
        try{
            sql = "insert into User (Taikhoan,MatKhau,Hoten,GioiTinh,NgaySinh,Sodt,Email) values('admin','admin','Nguyễn Huy Đông ','Nam','10012000', '0399999999','Huydong@gmail.com')";
            dp.execSQL(sql);
        }catch (Exception ex){
            Toast.makeText(this,"Khởi tạo giữ liệu thất bại "+ ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    private boolean isUser(String name , String passwork){
        try{
            dp = openOrCreateDatabase(DataBasename,MODE_PRIVATE,null);
            Cursor c = dp.rawQuery("select *from User where Taikhoan = ? and MatKhau =?",new String[]{name,passwork});
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