package com.example.sample_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sample_project.Adapter.BookAdapter;
import com.example.sample_project.DAO.SachDAO;
import com.example.sample_project.Models.Sach;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Quan_li_sachActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    BookAdapter adapter = null;
    SachDAO sachDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_li_sach);

        setTitle("QUẢN LÝ SÁCH");
        lvBook = (ListView) findViewById(R.id.lvBook);

        sachDAO = new SachDAO(this);
        dsSach = sachDAO.getAllSach();

        adapter = new BookAdapter(this, dsSach);
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Sach sach = (Sach) parent.getItemAtPosition(position);
                Intent intent = new Intent(Quan_li_sachActivity.this , Add_bookActivity.class);
                Bundle b = new Bundle();
                b.putString("MASACH", sach.getMaSach());
                b.putString("MATHELOAI", sach.getMaTheLoai());
                b.putString("TENSACH", sach.getTenSach());
                b.putString("TACGIA", sach.getTacGia());
                b.putString("NXB", sach.getNXB());
                b.putString("GIABIA", String.valueOf(sach.getGiaBia()));
                b.putString("SOLUONG", String.valueOf(sach.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        // TextFilter
        lvBook.setTextFilterEnabled(true);
        TextInputLayout edSeach = (TextInputLayout) findViewById(R.id.ed_SearchBook);

        edSeach.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] \n" +
                        "\t\t- Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_book:
                Intent intent = new Intent(this,Add_bookActivity.class);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sachDAO = new SachDAO(this);
        dsSach = sachDAO.getAllSach();

        adapter = new BookAdapter(this, dsSach);
        lvBook.setAdapter(adapter);
    }
    public void search_book(View view){
        adapter.notifyDataSetChanged();
        lvBook.setAdapter(adapter);
        Log.e("------------------" , "search ");
    }
}