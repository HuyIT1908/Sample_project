package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    TextInputLayout ed_user, edPass, edRePass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("ĐỔI MẬT KHẨU");

        ed_user = findViewById(R.id.ed_change_user_pw);
        edPass = (TextInputLayout) findViewById(R.id.ed_change_Password);
        edRePass = (TextInputLayout) findViewById(R.id.ed_change_re_Password);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String strUserName = pref.getString("USERNAME", "");
        ed_user.getEditText().setText(strUserName);
        ed_user.setEnabled(false);
        ed_user.setCounterEnabled(true);
        edPass.setCounterEnabled(true);
        edRePass.setCounterEnabled(true);
    }
    public void cancel_change_pw(View view){
        edPass.getEditText().setText("");
        edRePass.getEditText().setText("");
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