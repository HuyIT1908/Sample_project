package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sample_project.DAO.NguoiDungDAO;
import com.google.android.material.textfield.TextInputLayout;

public class NguoiDungDetailActivity extends AppCompatActivity {
    TextInputLayout edFullName, edPhone;
    NguoiDungDAO nguoiDungDAO;
    String username, fullname, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung_detail);

        setTitle("CHI TIẾT NGƯỜI DÙNG");
        edFullName = (TextInputLayout) findViewById(R.id.ed_update_full_name);
        edPhone = (TextInputLayout) findViewById(R.id.ed_update_phone);

        nguoiDungDAO = new NguoiDungDAO(this);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        fullname = b.getString("FULLNAME");
        phone = b.getString("PHONE");
        username = b.getString("USERNAME");
        edFullName.getEditText().setText(fullname);
        edPhone.getEditText().setText(phone);
    }

    public void updateUser(View view) {
        if (nguoiDungDAO.updateInfoNguoiDung(username,
                edPhone.getEditText().getText().toString(),
                edFullName.getEditText().getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void Huy(View view) {
        edPhone.getEditText().setText("");
        edFullName.getEditText().setText("");
    }
}