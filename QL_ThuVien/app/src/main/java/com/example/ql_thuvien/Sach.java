package com.example.ql_thuvien;

import java.io.Serializable;

public class Sach implements Serializable {
    int id;
    int MaSach;
    String TenSach;
    int Maloai;
    String Mota;
    byte [] Hinhanh;
    int LuonMuon;

    public Sach(String tenSach, String mota, byte[] hinhanh) {
        super();
        this.TenSach = tenSach;
        this.Mota = mota;
        this.Hinhanh = hinhanh;
    }

    public Sach(int Id , int maSach, String tenSach, int maloai, String mota, byte[] hinhanh, int luonMuon ) {
        super();
        this.MaSach = maSach;
        this.TenSach = tenSach;
        this.Maloai = maloai;
        this.Mota = mota;
        this.Hinhanh = hinhanh;
        this.LuonMuon = luonMuon;
        this.id = Id;
    }

    public Sach(int maSach, String tenSach, int maloai, String mota, byte[] hinhanh, int luonMuon) {

    }

    public Sach() {

    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getMaloai() {
        return Maloai;
    }

    public void setMaloai(int maloai) {
        Maloai = maloai;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public byte[] getHinhanh() {
        return Hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        Hinhanh = hinhanh;
    }

    public int getLuonMuon() {
        return LuonMuon;
    }

    public void setLuonMuon(int luonMuon) {
        LuonMuon = luonMuon;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
