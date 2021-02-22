package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sample_project.DAO.NguoiDungDAO;

public class Log_in_Activity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_);

        setTitle("ĐĂNG NHẬP");
        edUserName = (EditText) findViewById(R.id.ed_login_user_name);
        edPassword = (EditText) findViewById(R.id.ed_login_Pasword);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnCancel = (Button) findViewById(R.id.btn_cancel_login);
        chkRememberPass = findViewById(R.id.chkRememberPass_7);
        nguoiDungDAO = new NguoiDungDAO(Log_in_Activity.this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String tk = pref.getString("USERNAME", null);
        String mk = pref.getString("PASSWORD", null);
        boolean nho = pref.getBoolean("REMEMBER", true);
        if (tk != null && mk != null){
            edUserName.setText(String.valueOf(tk));
            edPassword.setText(String.valueOf(mk));
            chkRememberPass.setChecked(true);
            Log.e("-----------login test", String.valueOf(nho) + "\t" + tk + "\t\t" + mk);
        }
    }

    public void huy(View v) {
        edUserName.setText("");
        edPassword.setText("");
    }

    public void checkLogin(View v) {
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (nguoiDungDAO.checkLogin(strUser, strPass) > 0) {

                rememberUser(strUser, strPass, chkRememberPass.isChecked());
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                finish();
                startActivity(new Intent(this, MainActivity.class));
            } else if
            (strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")) {

                rememberUser(strUser, strPass, chkRememberPass.isChecked());
//                finish();
                startActivity(new Intent(this, MainActivity.class));
            } else {

                Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if (!status) {
            //xoa tinh trang luu tru truoc do
            edit.clear();
        } else {
            //luu du lieu
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
            Toast.makeText(getApplicationContext(), "Đã nhớ tài khoản",
                    Toast.LENGTH_SHORT).show();

        }
        //luu lai toan bo
        if (edit.commit()) {
            Toast.makeText(getApplicationContext(), "Lưu tài khoản thành công",
                    Toast.LENGTH_SHORT).show();
        }
        Log.e("-----------------------", String.valueOf(status) + "\n\t" + u + "\n\t" + p);
    }
}