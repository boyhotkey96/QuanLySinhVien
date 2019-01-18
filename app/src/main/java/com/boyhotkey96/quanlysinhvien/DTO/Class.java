package com.boyhotkey96.quanlysinhvien.DTO;

import java.io.Serializable;

/**
 * Created by boyhotkey96 on 17/08/2017.
 */

public class Class implements Serializable {

    private int _id;
    private String maLop;
    private String tenLop;

    public Class() {

    }

    public Class(int _id, String maLop, String tenLop) {
        this._id = _id;
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public Class(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }

    public int get_id() {
        return _id;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
