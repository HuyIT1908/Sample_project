package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

public class Add_the_loaiActivity extends AppCompatActivity {
    TextInputLayout ed_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_loai);

        ed_test = findViewById(R.id.ed_ma_the_loai);
        ed_test.getEditText().setText("dung thu xem the  nao nah");
    }
}