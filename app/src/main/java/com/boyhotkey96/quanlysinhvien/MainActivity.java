package com.boyhotkey96.quanlysinhvien;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.boyhotkey96.quanlysinhvien.DAO.ClassDAO;
import com.boyhotkey96.quanlysinhvien.DTO.Class;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnThemLop, btnXoaTrang, btnLuuLop, btnDanhSachLop, btnQuanLySinhVien;
    EditText etMaLop, etTenLop;
    AlertDialog dialog;

    List<Class> dsClass =  new ArrayList<Class>();
    //ClassDAO classDAO = new ClassDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);*/

        btnThemLop = (Button) findViewById(R.id.btnThemLop);
        btnDanhSachLop = (Button) findViewById(R.id.btnDanhSachLop);
        btnQuanLySinhVien = (Button) findViewById(R.id.btnQuanLySinhVien);

        ThemLop();
    }

    //-----------------------------------------------Thêm Lớp--------------------------------------------//
    public void ThemLop() {
        // Click button thêm lớp
        btnThemLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dialog thêm lớp
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View mview = getLayoutInflater().inflate(R.layout.dialog_themlop, null);

                etMaLop = (EditText) mview.findViewById(R.id.etMaLop);
                etTenLop = (EditText) mview.findViewById(R.id.etTenLop);
                btnLuuLop = (Button) mview.findViewById(R.id.btnLuuLop);
                btnXoaTrang = (Button) mview.findViewById(R.id.btnXoaTrang);

                LuuLopXoaTrang();

                builder.setView(mview);
                dialog = builder.create();
                dialog.show();
            }
        });
        DanhSachLop();
        QuanLySinhVien();
    }


    //-------------------------------Lưu Lớp và Xóa trắng-------------------------------------------------//
    public void LuuLopXoaTrang() {
        // Nhấn nút Lưu sẽ lưu dữ liệu nhập vào
        btnLuuLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etMaLop.getText().toString().trim().isEmpty() || etTenLop.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Mã lớp và Tên lớp", Toast.LENGTH_SHORT).show();
                } else {
                    Class cl = new Class();
                    cl.setMaLop(etMaLop.getText().toString());
                    cl.setTenLop(etTenLop.getText().toString());

                    //dsClass.add(cl);
                    ClassDAO classDAO = new ClassDAO(MainActivity.this);
                    classDAO.ThemLop(cl);

                    Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        // Nhấn bút Xóa sẽ làm lại
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etMaLop.setText("");
                etTenLop.setText("");
            }
        });
    }


    //----------------------------Load danh sách Lop -> Intent HienThiDanhSachLop--------------------------//
    public void DanhSachLop() {
        btnDanhSachLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HienThiDanhSachLop.class);
                startActivity(intent);
            }
        });
    }


    //-------------------------------------Quan ly Sinh Vien----------------------------------------------//
    public void QuanLySinhVien() {
        btnQuanLySinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuanLySinhVien.class);
                startActivity(intent);
            }
        });
    }


    //-----------------------------Optione Menu -> Thoát ứng dụng----------------------------------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.exit_optionmenu, menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exit_optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.thoat) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
