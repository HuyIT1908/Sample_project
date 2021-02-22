package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sample_project.DAO.SachDAO;
import com.example.sample_project.DAO.TheLoaiDAO;
import com.example.sample_project.Models.Sach;
import com.example.sample_project.Models.TheLoai;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Book_DetailActivity extends AppCompatActivity {
    TextInputLayout ed_ma_Sach, ed_ten_sach, ed_tac_gia, ed_NXB, ed_gia_bia, ed_so_luong;
    Spinner spn_ed_TheLoai;
    ImageView imv_add_the_loai;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    List<TheLoai> listTheLoai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__detail);

        ed_ma_Sach = findViewById(R.id.ed_ed_ma_sach);
        spn_ed_TheLoai = findViewById(R.id.spn_ed_TheLoai);
        imv_add_the_loai = findViewById(R.id.imv_ed_add_the_loai);
        ed_ten_sach = findViewById(R.id.ed_ed_ten_sach);
        ed_tac_gia = findViewById(R.id.ed_ed_tac_gia);
        ed_NXB = findViewById(R.id.ed_ed_NXB);
        ed_gia_bia = findViewById(R.id.ed_ed_gia_bia);
        ed_so_luong = findViewById(R.id.ed_ed_so_luong);
        getTheLoai();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        ed_ma_Sach.getEditText().setText(bundle.getString("MASACH"));
        spn_ed_TheLoai.setSelection(checkPositionTheLoai(bundle.getString("MATHELOAI")));
        ed_ten_sach.getEditText().setText(bundle.getString("TENSACH"));
        ed_tac_gia.getEditText().setText(bundle.getString("TACGIA"));
        ed_NXB.getEditText().setText(bundle.getString("NXB"));
        ed_gia_bia.getEditText().setText(bundle.getString("GIABIA"));
        ed_so_luong.getEditText().setText(bundle.getString("SOLUONG"));
    }

    public void update_book(View view) {
        String maSach, maTheLoai, tenSach, tacGia, nxb , giaBia , soLuong;

        maSach = ed_ma_Sach.getEditText().getText().toString();
        maTheLoai = spn_ed_TheLoai.getSelectedItem().toString();
        tenSach = ed_ten_sach.getEditText().getText().toString();
        tacGia = ed_tac_gia.getEditText().getText().toString();
        nxb = ed_NXB.getEditText().getText().toString();
        giaBia = ed_gia_bia.getEditText().getText().toString();
        soLuong = ed_so_luong.getEditText().getText().toString();
        int soLuong_2 = Integer.parseInt(soLuong);
//        Log.e(maSach + "\n" + maTheLoai + "\n"
//                + tenSach + "\n" + tacGia + "\n"
//                + nxb + "\n" + giaBia + "\n"
//                + soLuong + "\n", "  ------------");
//        Toast.makeText(getApplicationContext(),maSach + "\n" + maTheLoai.split(" | ")[0] + "\n"
//                + tenSach + "\n" + tacGia + "\n"
//                + nxb + "\n" + giaBia + "\n"
//                + soLuong + "\n", Toast.LENGTH_SHORT).show();
        try {
            Sach sach = new Sach(maSach , maTheLoai.split(" | ")[0],
                    tenSach , tacGia , nxb ,
                    0.0 , 3);
            if (sachDAO.updateSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        }catch (Exception ex){
            Log.e("Error Update edit book" , String.valueOf(ex));
        }
    }

    public void cancel_add_book(View view) {
        ed_ma_Sach.getEditText().setText("");
        ed_ten_sach.getEditText().setText("");
        ed_tac_gia.getEditText().setText("");
        ed_NXB.getEditText().setText("");
        ed_gia_bia.getEditText().setText("");
        ed_so_luong.getEditText().setText("");
    }

    public void add_the_loai(View view) {
        startActivity(new Intent(getApplicationContext(), Add_the_loaiActivity.class));
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
        spn_ed_TheLoai.setAdapter(dataAdapter);
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