package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sample_project.DAO.HoaDonChiTietDAO;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);

        setTitle("DOANH THU");
        tvNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvNgay.setText("Hôm nay: " + hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này: " + hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm này: " + hoaDonChiTietDAO.getDoanhThuTheoNam());
    }
}