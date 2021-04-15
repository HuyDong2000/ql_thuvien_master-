package com.example.ql_thuvien;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class EditSach extends AppCompatActivity {
    Button btnLuu;
    EditText edMaSach , edTenSach , edMaloai , edMota ;
    ImageView imgAnh;
    SQL db;
    SQLiteDatabase dp;
    int masach , loaisach , luotmuon;
    String tensach , mota;
    byte[] hinhanh;
    int values = 0 ;
    private int REQUEST_CODE = 115;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editthongtinsach);
        widget();
        Intent intent = getIntent();
        values = intent.getIntExtra("vt",0);

       getData(values);
        db=new SQL(this,"Qlthuvien.db",null,1);
       btnLuu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               masach = Integer.parseInt(edMaSach.getText().toString());
               tensach = edTenSach.getText().toString();
               loaisach = Integer.parseInt(edMaloai.getText().toString());
               mota = edMota.getText().toString();
               int id = values;
               hinhanh = ConverttoArrayByte(imgAnh);
               try {
                   db.updateStudent(masach,tensach,loaisach,mota,hinhanh,id);
                   Toast.makeText(getApplication(),"Sửa thành công",Toast.LENGTH_SHORT).show();
               }catch (Exception ex){
                   Toast.makeText(getApplication(),"Loi"+ex,Toast.LENGTH_SHORT).show();
               }
               Intent intent = new Intent(EditSach.this,Homeapp2.class);
               startActivity(intent);
           }
       });
       imgAnh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(Intent.ACTION_PICK);
               intent.setType("image/*");
               startActivityForResult(intent,REQUEST_CODE);
           }
       });
    }
    public void widget(){
        btnLuu = (Button)findViewById(R.id.Themsached);
        edMaSach = (EditText)findViewById(R.id.edMasached);
        edTenSach = (EditText)findViewById(R.id.edTensached);
        edMaloai = (EditText)findViewById(R.id.Maloaied);
        edMota = (EditText)findViewById(R.id.Motaed);
        imgAnh = (ImageView)findViewById(R.id.Hinhanhed);
    }
    private void getData(int id){
        Cursor cursor = MainActivity.db.TruyVanTraVe("Select * from Sach where ID ="+id+"");
        edMaSach.setText(String.valueOf(cursor.getCount()));
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
        edMaSach.setText(String.valueOf(s.getMaSach()));
        edTenSach.setText(s.getTenSach());
        edMaloai.setText(String.valueOf(s.getMaloai()));
        edMota.setText(s.getMota());
        Bitmap bitmap= BitmapFactory.decodeByteArray(s.getHinhanh(), 0, s.getHinhanh().length);
        imgAnh.setImageBitmap(bitmap);
    }

    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_CODE&&resultCode==RESULT_OK){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
