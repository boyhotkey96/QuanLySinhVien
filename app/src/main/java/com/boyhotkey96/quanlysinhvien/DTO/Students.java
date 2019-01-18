package com.boyhotkey96.quanlysinhvien.DTO;

import java.util.Date;

/**
 * Created by boyhotkey96 on 17/08/2017.
 */

public class Students {

    int _id;
    String tenSinhVien;
    String ngaySinh;

    public int get_id() {
        return _id;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
