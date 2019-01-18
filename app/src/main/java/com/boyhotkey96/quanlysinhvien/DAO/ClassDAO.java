package com.boyhotkey96.quanlysinhvien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.boyhotkey96.quanlysinhvien.DTO.Class;
import com.boyhotkey96.quanlysinhvien.DTO.Students;
import com.boyhotkey96.quanlysinhvien.SQLiteHelper.MyDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boyhotkey96 on 18/08/2017.
 */

public class ClassDAO {

    MyDatabase myDatabase;
    SQLiteDatabase database;

    public ClassDAO(Context context) {
        myDatabase = new MyDatabase(context);
    }

    public void open() {
        database = myDatabase.getWritableDatabase();
    }

    public void close() {
        myDatabase.close();
    }

    // Chức năng thêm lớp vào Database
    public void ThemLop(Class lop) {
        database = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.MALOP_TBLOP, lop.getMaLop().toString());
        values.put(MyDatabase.TENLOP_TBLOP, lop.getTenLop().toString());
        database.insert(MyDatabase.TB_LOP, null, values);
        myDatabase.close();
    }

    /*public boolean ThemLop(Class lop) {
        ContentValues values = new ContentValues();
        values.put(MyDatabase.MALOP_TBLOP, lop.getMaLop());
        values.put(MyDatabase.TENLOP_TBLOP, lop.getTenLop());

        long id_class = database.insert(MyDatabase.TB_LOP, null, values);
        if (id_class != 0) {
            return true;
        } else {
            return false;
        }
    }*/

    // Chức năng Load dữ liệu từ Database
    public List<Class> LoadDSClass() {
        List<Class> dsClass = new ArrayList<Class>();

        database = myDatabase.getReadableDatabase();
        String truyvan = "SELECT * FROM " + MyDatabase.TB_LOP;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String malop = cursor.getString(1);
            String tenlop = cursor.getString(2);

            Class cl = new Class();
            cl.set_id(cursor.getInt(0));
            cl.setMaLop(cursor.getString(1));
            cl.setTenLop(cursor.getString(2));

            dsClass.add(cl);

            cursor.moveToNext();
        }
        myDatabase.close();
        return dsClass;
    }

    // Chức năng xóa dữ liệu từ Database
    public boolean XoaLop(Class lop) {
        database = myDatabase.getReadableDatabase();
        //Argument
        //int kiemtra = database.delete(MyDatabase.TB_LOP, MyDatabase.ID_TBLOP + " = ?", new String[] {String.valueOf(lop.get_id())});

        int kiemtra = database.delete(MyDatabase.TB_LOP, MyDatabase.ID_TBLOP + " = " + lop.get_id(), null);
        myDatabase.close();
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }


    // Chức năng cập nhật dữ liệu
    public void CapNhatLop(Class lop) {
        // Chu y: id cu, ma lop & ten lop moi
        database = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.MALOP_TBLOP, lop.getMaLop().toString());
        values.put(MyDatabase.TENLOP_TBLOP, lop.getTenLop().toString());
        database.update(MyDatabase.TB_LOP, values, MyDatabase.ID_TBLOP + " = ?", new String[]{lop.get_id() + ""});
        myDatabase.close();
    }

    /*public boolean CapNhatLop(Class lopcu, Class lopmoi) {
        ContentValues values = new ContentValues();
        values.put(MyDatabase.TB_LOP, lopmoi.getMaLop().toString());
        values.put(MyDatabase.TB_LOP, lopmoi.getTenLop().toString());

        int kiemtra1 = database.update(MyDatabase.TB_LOP, values, MyDatabase.MALOP_TBLOP + " = " + lopcu.getMaLop().toString(), null);
        int kiemtra2 = database.update(MyDatabase.TB_LOP, values, MyDatabase.TENLOP_TBLOP + " = " + lopcu.getTenLop().toString(), null);

        if (kiemtra1 != 0 || kiemtra2 != 0) {
            return true;
        } else {
            return false;
        }
    }*/

    public List<String> layDuLieu() {
        List<String> list = new ArrayList<String>();

        String truyvan = "SELECT * FROM " + MyDatabase.TB_LOP;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tenlop = cursor.getString(2);
            list.add(tenlop);

            cursor.moveToNext();
        }
        database.close();
        return list;
    }

    // Thêm Sinh viên class Students
    public void ThemSinhVien(Students sv) {
        database = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.TENSINHVIEN_TBSINHVIEN, sv.getTenSinhVien().toString());
        values.put(MyDatabase.NGAYSINH_TBSINHVIEN, sv.getNgaySinh().toString());
        database.insert(MyDatabase.TB_SINHVIEN, null, values);
        myDatabase.close();
    }

    // Load Sinh viên class Students
    public List<Students> LoadDSStudents() {
        List<Students> dsStudents = new ArrayList<Students>();

        database = myDatabase.getReadableDatabase();
        String truyvan = "SELECT * FROM " + MyDatabase.TB_SINHVIEN;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tensv = cursor.getString(1);
            String ngaysinh = cursor.getString(2);

            Students sv = new Students();
            sv.setTenSinhVien(cursor.getString(1));
            sv.setNgaySinh(cursor.getString(2));

            dsStudents.add(sv);

            cursor.moveToNext();
        }
        myDatabase.close();
        return dsStudents;
    }

    // Xóa Sinh viên class Students
    public boolean XoaSV(Students sv) {
        int kiemtra = database.delete(MyDatabase.TB_SINHVIEN, MyDatabase.ID_TBSINHVIEN + " = " + sv.get_id(), null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }
}