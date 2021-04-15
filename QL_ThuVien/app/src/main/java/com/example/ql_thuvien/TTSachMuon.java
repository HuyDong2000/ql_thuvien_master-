package com.example.ql_thuvien;

import java.io.Serializable;

public class TTSachMuon implements Serializable {
    int Id;
    int Id_sach;
    int Id_user;

    public TTSachMuon(int id, int id_sach, int id_user) {
        this.Id = id;
        this.Id_sach = id_sach;
        this.Id_user = id_user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_sach() {
        return Id_sach;
    }

    public void setId_sach(int id_sach) {
        Id_sach = id_sach;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_user(int id_user) {
        Id_user = id_user;
    }
}
