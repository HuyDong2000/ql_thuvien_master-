package com.example.ql_thuvien;

public class LoaiSach {
    String Tenloai;
    int Maloai;

    public LoaiSach( int maloai , String tenloai) {
        super();
        this.Maloai = maloai;
        this.Tenloai = tenloai;
    }

    public String getTenloai() {
        return Tenloai;
    }

    public void setTenloai(String tenloai) {
        Tenloai = tenloai;
    }

    public int getMaloai() {
        return Maloai;
    }

    public void setMaloai(int maloai) {
        Maloai = maloai;
    }
}
