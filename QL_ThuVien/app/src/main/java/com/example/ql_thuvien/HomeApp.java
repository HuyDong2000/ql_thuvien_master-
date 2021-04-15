package com.example.ql_thuvien;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeApp  extends AppCompatActivity {
    Button btnThemSach;
   TextView Ten , TacGia ,LuotMuon;
   ImageView imganh;
   int values;
   String name;
   SQL db;
   int Luotmuon = 0;
   int ID_sach;
   int ID_user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeapp);
        btnThemSach = (Button)findViewById(R.id.Muon);
       Ten = (TextView)findViewById(R.id.Tensachtt);
       TacGia = (TextView)findViewById(R.id.Tacgiatt);
       LuotMuon = (TextView)findViewById(R.id.luotmuontt);
        imganh = (ImageView)findViewById(R.id.imgAnhsach);

        Intent intent = getIntent();
        values = intent.getIntExtra("vt",0);
        name = intent.getStringExtra("name");
        db=new SQL(this,"Qlthuvien.db",null,1);
        getData(values );
        getdataUser(name);
        btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.updatemuon(Luotmuon,values);
                    db.InsertTTSach(ID_sach,ID_user);
                    Toast.makeText(getApplication(),"Mượn Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeApp.this,Homeapp2.class);
                    intent.putExtra("id",ID_user);
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(getApplication(),"Loi"+ex,Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void getData(int id){
        Cursor cursor = MainActivity.db.TruyVanTraVe("Select * from Sach where ID ="+id+"");
        Sach s = new Sach();
        try {
            if(cursor.moveToFirst()){
                s.setId(cursor.getInt(0));
                s.setMaSach(cursor.getInt(1));
                s.setTenSach(cursor.getString(2));
                s.setMaloai(cursor.getInt(3));
                s.setMota(cursor.getString(4));
                s.setHinhanh(cursor.getBlob(5));
                s.setLuonMuon(cursor.getInt(6));
            }
//
        }catch (Exception ex){
//            Toast.makeText(getApplication(),"Loi"+ex,Toast.LENGTH_SHORT).show();
        }
        ID_sach = s.getId();
        Luotmuon = s.getLuonMuon()+1;
        Ten.setText(s.getTenSach());
        TacGia.setText(s.getMota());
        LuotMuon.setText("Lượt Mượn : "+String.valueOf(s.getLuonMuon()));
        Bitmap bitmap= BitmapFactory.decodeByteArray(s.getHinhanh(), 0, s.getHinhanh().length);
        imganh.setImageBitmap(bitmap);
    }
    public void getdataUser(String name )
    {
        Cursor cursor = MainActivity.db.TruyVanTraVe("Select * from User where Taikhoan  = '"+name+"'");
        User user = new User();
        try {
            if(cursor.moveToFirst()){
                user.setId(cursor.getInt(0));
            }
        }catch (Exception ex){
            Toast.makeText(getApplication(),"Loi"+ex,Toast.LENGTH_SHORT).show();
        }
        ID_user = user.getId();
    }

}
