package com.example.ql_thuvien;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemSach extends AppCompatActivity {
    Button btnThem;
    EditText edMasach , edTensach , edMaloai,edMota;
    ImageView imgAnh;
    SQL db;
    SQLiteDatabase dp;
    int masach , loaisach , luotmuon;
    String tensach , mota;
    byte[] hinhanh;
    String values;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themsach);

        btnThem = (Button)findViewById(R.id.Themsach);
        edMasach = (EditText)findViewById(R.id.edMasach);
        edTensach = (EditText)findViewById(R.id.edTensach);
        edMaloai = (EditText)findViewById(R.id.Maloai);
        edMota = (EditText)findViewById(R.id.Mota);
        imgAnh = (ImageView)findViewById(R.id.Hinhanh);
        Intent intent = getIntent();
        values = intent.getStringExtra("vt");
        db=new SQL(this,"Qlthuvien.db",null,1);
        imgAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masach = Integer.parseInt(edMasach.getText().toString());
                tensach = edTensach.getText().toString();
                loaisach = Integer.parseInt(edMaloai.getText().toString());
                mota = edMota.getText().toString();
                luotmuon = 0;
                hinhanh = ConverttoArrayByte(imgAnh);
                try {
                    db.InsertSach(masach,tensach,loaisach,mota,hinhanh,luotmuon);
                    Toast.makeText(getApplication(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(getApplication(),"Loi"+ex,Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(ThemSach.this,Homeapp2.class);
                intent.putExtra("vt",values);
                startActivity(intent);
            }
        });
    }
    private int REQUEST_CODE = 1;

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
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
