package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sample_project.DAO.NguoiDungDAO;
import com.example.sample_project.DAO.TheLoaiDAO;
import com.example.sample_project.Models.NguoiDung;
import com.example.sample_project.Models.TheLoai;
import com.google.android.material.textfield.TextInputLayout;

public class Add_the_loaiActivity extends AppCompatActivity {
    TextInputLayout ed_maTL, ed_tenTL, ed_vi_triTL, ed_mo_taTL;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_loai);

        ed_maTL = findViewById(R.id.ed_ma_the_loai);
        ed_tenTL = findViewById(R.id.ed_ten_the_loai);
        ed_vi_triTL = findViewById(R.id.ed_vi_tri);
        ed_mo_taTL = findViewById(R.id.ed_mo_ta);
    }
    public void cancel_btn(View view){
        ed_maTL.getEditText().setText("");
        ed_tenTL.getEditText().setText("");
        ed_mo_taTL.getEditText().setText("");
        ed_vi_triTL.getEditText().setText("");
    }

    public void show_the_loai(View view) {
        finish();
    }

    public void add_the_loai(View view) {
        theLoaiDAO = new TheLoaiDAO(this);

        try {
            TheLoai theLoai = new TheLoai(ed_maTL.getEditText().getText().toString(),
                    ed_tenTL.getEditText().getText().toString(),
                    ed_mo_taTL.getEditText().getText().toString(),
                    Integer.parseInt(ed_vi_triTL.getEditText().getText().toString()));
            if (validateForm() > 0) {
                if (theLoaiDAO.inserTheLoai(theLoai) > 0) {

                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error  -- ", ex.toString());
        }
    }

    public int validateForm() {
        int check = 1;
        if (ed_maTL.getEditText().getText().length() == 0
                || ed_tenTL.getEditText().getText().length() == 0
                || ed_mo_taTL.getEditText().getText().length() == 0
                || ed_vi_triTL.getEditText().getText().length() == 0) {

            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}