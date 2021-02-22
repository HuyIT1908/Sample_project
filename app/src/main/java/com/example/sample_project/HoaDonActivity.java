package com.example.sample_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sample_project.DAO.HoaDonDAO;
import com.example.sample_project.Models.HoaDon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HoaDonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText edNgayMua, edMaHoaDon;
    HoaDonDAO hoaDonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        setTitle("THÊM HOÁ ĐƠN");
        edNgayMua = (EditText) findViewById(R.id.ed_NgayMua);
        edMaHoaDon = (EditText) findViewById(R.id.ed_MaHoaDon);
    }
    public void cancel(View view){
        edMaHoaDon.setText("");
        edNgayMua.setText("");
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate(cal);
    }
    private void setDate(final Calendar calendar) {
        edNgayMua.setText(sdf.format(calendar.getTime()));
    }
    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }
    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
    }
    public void ADDHoaDon(View view) {
        hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                HoaDon hoaDon = new
                        HoaDon(edMaHoaDon.getText().toString(),sdf.parse(edNgayMua.getText().toString()));
                if (hoaDonDAO.inserHoaDon(hoaDon) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HoaDonActivity.this , HoaDonChiTietActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MAHOADON", edMaHoaDon.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
        finish();
    }
    public int validation(){
        if (edMaHoaDon.getText().toString().isEmpty()||edNgayMua.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}