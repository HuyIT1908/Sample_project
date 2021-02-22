package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sample_project.Adapter.CartAdapter;
import com.example.sample_project.DAO.HoaDonChiTietDAO;
import com.example.sample_project.Models.HoaDon;
import com.example.sample_project.Models.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonChiTietByIDActivity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don_chi_tiet_by_i_d);

        setTitle("HOÁ ĐƠN CHI TIẾT");
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ListHoaDonChiTietByIDActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
        lvCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDonChiTiet hoaDonChiTiet = (HoaDonChiTiet) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListHoaDonChiTietByIDActivity.this, HoaDonChiTietActivity.class);
                Bundle b = new Bundle();
                b.putString("MAHOADON", hoaDonChiTiet.getHoaDon().getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}