package com.example.ql_thuvien;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

public class SQL extends SQLiteOpenHelper {
    SQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    void TruyVanKhongTraVe(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }

    Cursor TruyVanTraVe(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        return db.rawQuery(sql,null);
    }
    void InsertSach(int MaSach , String TenSach , int Maloai , String Mota , byte [] Hinhanh , int LuonMuon)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into Sach values (null,?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1,MaSach);
        statement.bindString(2,TenSach);
        statement.bindLong(3,Maloai);
        statement.bindString(4,Mota);
        statement.bindBlob(5,Hinhanh);
        statement.bindLong(6,LuonMuon);
        statement.executeInsert();
    }
    void InsertTTSach(int Sach , int user )
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into TTSachMuon values (null,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1,Sach);
        statement.bindLong(2,user);
        statement.executeInsert();
    }
    void Iseruser(String Taikhoan,
            String MatKhau,
            String Hoten,
            String GioiTinh,
            String NgaySinh,
            String Sodt,
            String Email)
    {
            SQLiteDatabase db=getWritableDatabase();
            String sql="Insert into User values (null,?,?,?,?,?,?,?)";
            SQLiteStatement statement=db.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1,Taikhoan);
            statement.bindString(2,MatKhau);
            statement.bindString(3,Hoten);
            statement.bindString(4,GioiTinh);
            statement.bindString(5,NgaySinh);
            statement.bindString(6,Sodt);
            statement.bindString(7,Email);
            statement.executeInsert();
    }
    public void updateStudent(int Masach , String Tensach , int Maloai , String Mota , byte[] Hinh , int id ) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Masach", Masach);
            values.put("TenSach", Tensach);
            values.put("Maloai",Maloai );
            values.put("Mota",Mota );
            values.put("Hinhanh",Hinh );
            db.update("Sach", values, "ID" + " = ?", new String[] { String.valueOf(id) });
            db.close();
        }catch (Exception ex){

        }

    }
    public  void UpdateUser(int id , String Taikhoan , String MatKhau , String Hoten ,String GioiTinh ,String NgaySinh ,String Sodt , String Email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Taikhoan",Taikhoan);
        values.put("MatKhau",MatKhau);
        values.put("Hoten",Hoten);
        values.put("GioiTinh",GioiTinh);
        values.put("NgaySinh",NgaySinh);
        values.put("Sodt",Sodt);
        values.put("Email",Email);
        db.update("User",values,"ID"+"=?",new String[] {String.valueOf(id)});
        db.close();
    }
    public void updatemuon(int LuotMuon , int id  ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("LuonMuon",LuotMuon );
        db.update("Sach", values, "ID" + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }
    public void updatMatkhau(String Taikhoan , String mk ) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("MatKhau",mk );
            db.update("User", values, "Taikhoan" + " = ?", new String[] {Taikhoan});
            db.close();
        }catch (Exception ex){

        }

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

