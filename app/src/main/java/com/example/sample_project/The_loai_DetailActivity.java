package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sample_project.DAO.TheLoaiDAO;
import com.example.sample_project.Models.TheLoai;
import com.google.android.material.textfield.TextInputLayout;

public class The_loai_DetailActivity extends AppCompatActivity {
    TextInputLayout ed_ma_TL, ed_ten_TL , ed_mo_ta, ed_vi_tri;
    TheLoaiDAO theLoaiDAO;
    String maTL, tenTL, moTaTL, viTriTL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai__detail);

        setTitle("Chi tiết thể loại");
        ed_ma_TL = findViewById(R.id.ed_edit_maTL);
        ed_ten_TL = findViewById(R.id.ed_edit_ten_TL);
        ed_mo_ta = findViewById(R.id.ed_edit_mo_ta);
        ed_vi_tri = findViewById(R.id.ed_edit_vi_tri);

        theLoaiDAO = new TheLoaiDAO(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        maTL = bundle.getString("MATHELOAI");
        tenTL = bundle.getString("TENTHELOAI");
        moTaTL = bundle.getString("MOTA");
        viTriTL = bundle.getString("VITRI");

        ed_ma_TL.setEnabled(false);
        ed_ma_TL.getEditText().setText(maTL);
        ed_ten_TL.getEditText().setText(tenTL);
        ed_mo_ta.getEditText().setText(moTaTL);
        ed_vi_tri.getEditText().setText(viTriTL);
    }
    public void update_the_loai(View view){
        TheLoai theLoai = new TheLoai();
        theLoai.setMaTheLoai(ed_ma_TL.getEditText().getText().toString());
        theLoai.setTenTheLoai(ed_ten_TL.getEditText().getText().toString());
        theLoai.setMoTa(ed_mo_ta.getEditText().getText().toString());
        theLoai.setViTri(Integer.parseInt(ed_vi_tri.getEditText().getText().toString()));

        if (theLoaiDAO.updateTheLoai(theLoai) > 0){
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void edit_huy(View view){
        ed_ma_TL.getEditText().setText("");
        ed_ten_TL.getEditText().setText("");
        ed_mo_ta.getEditText().setText("");
        ed_vi_tri.getEditText().setText("");
    }
}