package com.boyhotkey96.quanlysinhvien.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by boyhotkey96 on 17/08/2017.
 */

public class MyDatabase extends SQLiteOpenHelper {

    // Database
    public static final String DB_NAME = "QLSV";
    public static final int DB_VERSION = 1;
    // Table LOP
    public static final String TB_LOP = "LOP";
    public static final String ID_TBLOP = "_id";
    public static final String MALOP_TBLOP = "maLop";
    public static final String TENLOP_TBLOP= "tenLop";
    // Table SINHVIEN
    public static final String TB_SINHVIEN = "SINHVIEN";
    public static final String ID_TBSINHVIEN = "_id";
    public static final String TENSINHVIEN_TBSINHVIEN = "tenSinhVien";
    public static final String NGAYSINH_TBSINHVIEN = "ngaySinh";

    public MyDatabase(Context context) {
        super(context, "DB_NAME", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create table LOP
        String sql1 = "CREATE TABLE "
                    + TB_LOP
                    + " ("
                    + ID_TBLOP
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + MALOP_TBLOP
                    + " TEXT, "
                    + TENLOP_TBLOP
                    + " TEXT)";
        sqLiteDatabase.execSQL(sql1);

        // Create table SINHVIEN
        String sql2 = "CREATE TABLE "
                    + TB_SINHVIEN
                    + " ("
                    + ID_TBSINHVIEN
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TENSINHVIEN_TBSINHVIEN
                    + " TEXT, "
                    + NGAYSINH_TBSINHVIEN
                    + " DATE)";
        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_LOP);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_SINHVIEN);
        onCreate(sqLiteDatabase);
    }
}
