package com.example.ql_thuvien;

public class User {
    String Taikhoan;
    String MatKhau;
    String Hoten;
    String GioiTinh;
    String NgaySinh;
    String Sodt;
    String Email;
    int id;

    public User(String taikhoan, String matKhau, String hoten, String gioiTinh, String ngaySinh, String sodt, String email) {
        super();
        this.Taikhoan = taikhoan;
        this.MatKhau = matKhau;
        this.Hoten = hoten;
        this.GioiTinh = gioiTinh;
        this.NgaySinh = ngaySinh;
        this.Sodt = sodt;
        this.Email = email;
    }

    public User(String taikhoan, String matKhau, String hoten, String gioiTinh, String ngaySinh, String sodt, String email, int id) {
        super();
        Taikhoan = taikhoan;
        MatKhau = matKhau;
        Hoten = hoten;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        Sodt = sodt;
        Email = email;
        this.id = id;
    }

    public User() {

    }

    public String getTaikhoan() {
        return Taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        Taikhoan = taikhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getSodt() {
        return Sodt;
    }

    public void setSodt(String sodt) {
        Sodt = sodt;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
