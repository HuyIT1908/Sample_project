package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sample_project.DAO.SachDAO;
import com.example.sample_project.DAO.TheLoaiDAO;
import com.example.sample_project.Models.Sach;
import com.example.sample_project.Models.TheLoai;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Add_bookActivity extends AppCompatActivity {
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spnTheLoai;
    TextInputLayout edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        setTitle("THÊM SÁCH");
        spnTheLoai = (Spinner) findViewById(R.id.spn_the_loai);
        getTheLoai();
        edMaSach = (TextInputLayout) findViewById(R.id.ed_ma_sach);
        edTenSach = (TextInputLayout) findViewById(R.id.ed_ten_sach);
        edNXB = (TextInputLayout) findViewById(R.id.ed_nxb);
        edTacGia = (TextInputLayout) findViewById(R.id.ed_tac_gia);
        edGiaBia = (TextInputLayout) findViewById(R.id.ed_gia_bia);
        edSoLuong = (TextInputLayout) findViewById(R.id.ed_so_luong);
        //
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int
                    position, long id) {
                maTheLoai =
                        listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaSach.getEditText().setText(b.getString("MASACH"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenSach.getEditText().setText(b.getString("TENSACH"));
            edNXB.getEditText().setText(b.getString("NXB"));
            edTacGia.getEditText().setText(b.getString("TACGIA"));
            edGiaBia.getEditText().setText(b.getString("GIABIA"));
            edSoLuong.getEditText().setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }
    }

    public void showSpinner(View view) {
        sachDAO = new SachDAO(this);
        sachDAO.getAllSach();
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }

    public void addBook(View view) {
        sachDAO = new SachDAO(this);
        Sach sach = new Sach(edMaSach.getEditText().getText().toString(),
                maTheLoai,
                edTenSach.getEditText().getText().toString(),
                edTacGia.getEditText().getText().toString(),
                edNXB.getEditText().getText().toString(),
                Double.parseDouble(edGiaBia.getEditText().getText().toString()),
                Integer.parseInt(edSoLuong.getEditText().getText().toString()));
        try {
            if (sachDAO.inserSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void showBook(View view) {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheLoai())) {
                return i;
            }
        }
        return 0;
    }
}