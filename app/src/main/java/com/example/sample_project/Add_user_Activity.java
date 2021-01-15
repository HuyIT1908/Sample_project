package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sample_project.DAO.NguoiDungDAO;
import com.example.sample_project.Models.NguoiDung;
import com.google.android.material.textfield.TextInputLayout;

public class Add_user_Activity extends AppCompatActivity {
    Button btnThemNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    TextInputLayout edUser, edPass, edRePass, edPhone, edFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_);

        setTitle("THÊM NGƯỜI DÙNG");
        btnThemNguoiDung = (Button) findViewById(R.id.btn_add_user);
        edUser = (TextInputLayout) findViewById(R.id.ed_add_user_name);
        edPass = (TextInputLayout) findViewById(R.id.ed_add_Password);
        edPhone = (TextInputLayout) findViewById(R.id.ed_add_phone);
        edFullName = (TextInputLayout) findViewById(R.id.ed_add_full_name);
        edRePass = (TextInputLayout) findViewById(R.id.ed_add_re_Password);
    }

    public void showUsers(View view) {
        finish();
    }

    public void addUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(Add_user_Activity.this);
        NguoiDung user = new NguoiDung(edUser.getEditText().getText().toString(),
                edPass.getEditText().getText().toString(),
                edPhone.getEditText().getText().toString(),
                edFullName.getEditText().getText().toString());
        try {
            if (validateForm() > 0) {
                if (nguoiDungDAO.inserNguoiDung(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int validateForm() {
        int check = 1;
        if (edUser.getEditText().getText().length() == 0
                || edFullName.getEditText().getText().length() == 0
                || edPhone.getEditText().getText().length() == 0
                || edPass.getEditText().getText().length() == 0
                || edRePass.getEditText().getText().length() == 0) {

            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edPass.getEditText().getText().toString();
            String rePass = edRePass.getEditText().getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}