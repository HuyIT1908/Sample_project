package com.example.sample_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sample_project.Adapter.NguoiDungAdapter;
import com.example.sample_project.DAO.NguoiDungDAO;
import com.example.sample_project.Models.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class Quan_li_ds_userActivity extends AppCompatActivity {
    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoiDungAdapter adapter = null;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_ds_user);

        setTitle("NGƯỜI DÙNG");
        lvNguoiDung = (ListView) findViewById(R.id.lvNguoiDung);
        nguoiDungDAO = new NguoiDungDAO(Quan_li_ds_userActivity.this);
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter = new NguoiDungAdapter(this, dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);

        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
//                edit info nguoi dung
                Intent intent = new
                        Intent(Quan_li_ds_userActivity.this, NguoiDungDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("USERNAME", dsNguoiDung.get(position).getUserName());
                b.putString("PHONE", dsNguoiDung.get(position).getPhone());
                b.putString("FULLNAME", dsNguoiDung.get(position).getHoTen());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int
                    position, long id) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter.changeDataset(nguoiDungDAO.getAllNguoiDung());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add_user:
                intent = new
                        Intent(Quan_li_ds_userActivity.this, Add_user_Activity.class);
                startActivity(intent);
                return (true);
            case R.id.changePass:
                intent = new
                        Intent(Quan_li_ds_userActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                return (true);
            case R.id.logOut:
//                SharedPreferences pref =
//                        getSharedPreferences("USER_FILE", MODE_PRIVATE);
//                SharedPreferences.Editor edit = pref.edit();
//                //xoa tinh trang luu tru truoc do
//                edit.clear();
//                edit.commit();
                intent = new Intent(Quan_li_ds_userActivity.this, Log_in_Activity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}