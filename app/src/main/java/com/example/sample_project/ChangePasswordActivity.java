package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sample_project.DAO.NguoiDungDAO;
import com.example.sample_project.Models.NguoiDung;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordActivity extends AppCompatActivity {
    TextInputLayout edPass, edRePass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("ĐỔI MẬT KHẨU");

        edPass = (TextInputLayout) findViewById(R.id.ed_change_Password);
        edRePass = (TextInputLayout) findViewById(R.id.ed_change_re_Password);
    }

    public int validateForm() {
        int check = 1;
        if (edPass.getEditText().getText().length() == 0 || edRePass.getEditText().getText().length() == 0) {
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

    public void changePassword(View view) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String strUserName = pref.getString("USERNAME", "");

        nguoiDungDAO = new NguoiDungDAO(ChangePasswordActivity.this);
        NguoiDung user = new NguoiDung(strUserName, edPass.getEditText().getText().toString(), "",
                "");
        try {
            if (validateForm() > 0) {
                if (nguoiDungDAO.changePasswordNguoiDung(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Lưu thành công",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Lưu thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
}