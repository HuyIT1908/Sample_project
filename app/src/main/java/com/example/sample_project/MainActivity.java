package com.example.sample_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sample_project.Adapter.Grid_adapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    List<String> name_grid = new ArrayList<>();
    List<Integer> img_grid = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gv_main);

        String[] ten_grid = {"Người dùng", "Thể loại",
                "Sách", "Hóa đơn",
                "Sách bán chạy", "Thống kê"};
        int[] anh_grid = {R.drawable.emthree, R.drawable.cateicon,
                R.drawable.bookicon, R.drawable.hdicon,
                R.drawable.money_icon, R.drawable.thongke};
        for (int i = 0; i < anh_grid.length; i++) {
            name_grid.add(ten_grid[i]);
            img_grid.add(anh_grid[i]);
        }

        Grid_adapter grid_adapter = new Grid_adapter(this, name_grid, img_grid);
        gridView.setAdapter(grid_adapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (name_grid.get(position).equalsIgnoreCase("Người dùng")) {


                    Intent intent = new Intent(MainActivity.this, Quan_li_ds_userActivity.class);
                    startActivity(intent);

                } else if (name_grid.get(position).equalsIgnoreCase("Thể loại")) {

                    startActivity(new Intent(MainActivity.this , Quan_li_ds_tlActivity.class));


                }else if (name_grid.get(position).equalsIgnoreCase("Sách")) {

                    startActivity(new Intent(MainActivity.this , Quan_li_sachActivity.class));

                }else if (name_grid.get(position).equalsIgnoreCase("Hóa đơn")) {

                    startActivity(new Intent(MainActivity.this , Quan_li_hoa_donActivity.class));

                }else if (name_grid.get(position).equalsIgnoreCase("Sách bán chạy")) {

                    startActivity(new Intent(MainActivity.this , LuotSachBanChayActivity.class ));

                }else if (name_grid.get(position).equalsIgnoreCase("Thống kê")) {

                    startActivity(new Intent(MainActivity.this , ThongKeDoanhThuActivity.class));

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.log_out_main:
                startActivity(new Intent(MainActivity.this , Log_in_Activity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}