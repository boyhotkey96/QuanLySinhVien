package com.boyhotkey96.quanlysinhvien;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.boyhotkey96.quanlysinhvien.Adapter.CustomClassAdapter;
import com.boyhotkey96.quanlysinhvien.DAO.ClassDAO;
import com.boyhotkey96.quanlysinhvien.DTO.Class;

import java.util.ArrayList;
import java.util.List;

public class HienThiDanhSachLop extends AppCompatActivity {

    List<Class> dsClass;
    ClassDAO classDAO;
    ListView lvHT;
    CustomClassAdapter adapter;
    AlertDialog dialog;

    Class laylop;
    Button btnYesSua, btnNoSua;
    EditText etSuaMaLop, etSuaTenLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hienthidanhsachlop);
        lvHT = (ListView) findViewById(R.id.lvHT);

        dsClass = new ArrayList<Class>();

        classDAO = new ClassDAO(this);
        classDAO.open();

        dsClass = classDAO.LoadDSClass();

        adapter = new CustomClassAdapter(HienThiDanhSachLop.this, R.layout.list_item, dsClass);
        lvHT.setAdapter(adapter);

        // Sự kiện nhấn giữ lâu vào item listview
        lvHT.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                String laymalop = dsClass.get(position).getMaLop();
                String laytenlop = dsClass.get(position).getTenLop();

                AlertDialog.Builder builderDelete = new AlertDialog.Builder(HienThiDanhSachLop.this);
                builderDelete.setIcon(R.drawable.delete);
                builderDelete.setTitle("Xóa");
                builderDelete.setMessage("Bạn có muốn xóa [" + laymalop + " - " + laytenlop + "] không?");
                builderDelete.setCancelable(false);
                builderDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Tạo position i
                        /*AdapterView.AdapterContextMenuInfo menuInfo = item.getMenuInfo();
                        int i = menuInfo.position;*/

                        Class cl = dsClass.get(position);
                        classDAO.XoaLop(cl);

                        adapter.remove(cl);
                        adapter.notifyDataSetChanged();
                    }
                });
                builderDelete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Khong thuc hien gi ca.
                    }
                });
                builderDelete.setNeutralButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builderUpdate = new AlertDialog.Builder(HienThiDanhSachLop.this);
                        View viewsua = getLayoutInflater().inflate(R.layout.dialog_sualop, null);

                        laylop = dsClass.get(position);

                        etSuaMaLop = (EditText) viewsua.findViewById(R.id.etSuaMaLop);
                        etSuaTenLop = (EditText) viewsua.findViewById(R.id.etSuaTenLop);
                        btnYesSua = (Button) viewsua.findViewById(R.id.btnYesSua);
                        btnNoSua = (Button) viewsua.findViewById(R.id.btnNoSua);

                        etSuaMaLop.setText(laylop.getMaLop());
                        etSuaTenLop.setText(laylop.getTenLop());

                        btnYesSua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Class lop = new Class(laylop.get_id(), etSuaMaLop.getText().toString(), etSuaTenLop.getText().toString());
                                classDAO.CapNhatLop(lop);
                                Toast.makeText(HienThiDanhSachLop.this, "Upate thành công", Toast.LENGTH_SHORT).show();

                                dsClass = classDAO.LoadDSClass();
                                adapter = new CustomClassAdapter(HienThiDanhSachLop.this, R.layout.list_item, dsClass);
                                lvHT.setAdapter(adapter);

                                dialog.dismiss();
                            }
                        });
                        btnNoSua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(HienThiDanhSachLop.this, "Hủy", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        builderUpdate.setView(viewsua);
                        dialog = builderUpdate.create();
                        dialog.show();
                    }
                });

                dialog = builderDelete.create();
                dialog.show();
                //builder.show();

                //Bắt sự kiện Onclick cho BUTTON_NEUTRAL
                /*dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HienThiDanhSachLop.this, SuaClass.class);
                        startActivity(intent);
                    }
                });*/
                return true;
            }
        });
    }
}
