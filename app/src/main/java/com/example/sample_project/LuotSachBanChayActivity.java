package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sample_project.Adapter.BookAdapter;
import com.example.sample_project.DAO.SachDAO;
import com.example.sample_project.Models.Sach;

import java.util.ArrayList;
import java.util.List;

public class LuotSachBanChayActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    BookAdapter adapter = null;
    SachDAO sachDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luot_sach_ban_chay);

        setTitle("TOP 10 SÁCH BÁN CHẠY");
        lvBook = (ListView) findViewById(R.id.lvBookTop);
        edThang = (EditText) findViewById(R.id.edThang);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext() , "Chức năng chưa hoàn thiện",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void VIEW_SACH_TOP_10(View view) {
        if (Integer.parseInt(edThang.getText().toString()) > 12 ||
                Integer.parseInt(edThang.getText().toString()) < 1) {
            Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng ( 1 - 12 )",
                    Toast.LENGTH_SHORT).show();
        } else {
            sachDAO = new SachDAO(LuotSachBanChayActivity.this);
            dsSach = sachDAO.getSachTop10(edThang.getText().toString());
            adapter = new BookAdapter(this, dsSach);
            lvBook.setAdapter(adapter);
        }
    }
}