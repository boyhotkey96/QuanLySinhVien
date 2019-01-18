package com.boyhotkey96.quanlysinhvien;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.boyhotkey96.quanlysinhvien.Adapter.CustomClassAdapter;
import com.boyhotkey96.quanlysinhvien.Adapter.CustomStudentsAdapter;
import com.boyhotkey96.quanlysinhvien.DAO.ClassDAO;
import com.boyhotkey96.quanlysinhvien.DTO.Class;
import com.boyhotkey96.quanlysinhvien.DTO.Students;

import java.util.List;

public class QuanLySinhVien extends AppCompatActivity {

    Spinner spinner;
    ClassDAO classDAO;
    SQLiteDatabase database;

    EditText etTenSinhVien, etNgaySinh;
    Button btThemSinhVien;
    ListView lvSV;

    List<Students> dsStudents;
    CustomStudentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sinh_vien);
        spinner = (Spinner) findViewById(R.id.spinner);
        etTenSinhVien = (EditText) findViewById(R.id.etTenSinhVien);
        etNgaySinh = (EditText) findViewById(R.id.etNgaySinh);
        btThemSinhVien = (Button) findViewById(R.id.btThemSinhVien);
        lvSV = (ListView) findViewById(R.id.lvSV);

        classDAO = new ClassDAO(this);
        classDAO.open();

        loadSpinner();

        dsStudents = classDAO.LoadDSStudents();
        adapter = new CustomStudentsAdapter(QuanLySinhVien.this, R.layout.listitemsv, dsStudents);
        lvSV.setAdapter(adapter);

        lvSV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Students sv = dsStudents.get(i);
                classDAO.XoaSV(sv);

                adapter.remove(sv);
                adapter.notifyDataSetChanged();

                return true;
            }
        });

        XuLyThemSV();
    }

    private void loadSpinner() {
        List<String> list = classDAO.layDuLieu();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(dataAdapter);
    }

    private void XuLyThemSV() {
        btThemSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Students sv = new Students();
                sv.setTenSinhVien(etTenSinhVien.getText().toString());
                sv.setNgaySinh(etNgaySinh.getText().toString());

                ClassDAO classDAO = new ClassDAO(QuanLySinhVien.this);
                classDAO.ThemSinhVien(sv);

                dsStudents = classDAO.LoadDSStudents();
                adapter = new CustomStudentsAdapter(QuanLySinhVien.this, R.layout.listitemsv, dsStudents);
                lvSV.setAdapter(adapter);
            }
        });
    }
}
