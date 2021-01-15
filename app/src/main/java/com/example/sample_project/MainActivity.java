package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

                    Toast.makeText(getApplicationContext(), "Người dùng",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Add_bookActivity.class);
                    startActivity(intent);

                } else if (name_grid.get(position).equalsIgnoreCase("Thể loại")) {

                    Toast.makeText(getApplicationContext(), "Thể loại",
                            Toast.LENGTH_SHORT).show();

                }else if (name_grid.get(position).equalsIgnoreCase("Sách")) {

                    Toast.makeText(getApplicationContext(), "Sách",
                            Toast.LENGTH_SHORT).show();

                }else if (name_grid.get(position).equalsIgnoreCase("Hóa đơn")) {

                    Toast.makeText(getApplicationContext(), "Hóa đơn",
                            Toast.LENGTH_SHORT).show();

                }else if (name_grid.get(position).equalsIgnoreCase("Sách bán chạy")) {

                    Toast.makeText(getApplicationContext(), "Sách bán chạy",
                            Toast.LENGTH_SHORT).show();

                }else if (name_grid.get(position).equalsIgnoreCase("Thống kê")) {

                    Toast.makeText(getApplicationContext(), "Thống kê",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}