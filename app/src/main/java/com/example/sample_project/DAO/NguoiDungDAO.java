package com.example.sample_project.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sample_project.Models.NguoiDung;
import com.example.sample_project.Sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    //    ten table
    public static final String TABLE_NAME = "NguoiDung";
    //    create table
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung (username text primary key," +
            " password text," +
            " phone text," +
            " hoten text);";
    public static final String TAG = "NguoiDungDAO";

    public NguoiDungDAO(Context context) {
//        tao doi tuong va lay quyen doc ghi
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserNguoiDung(NguoiDung nd) {
//        them nguoi dung vao db
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoTen());
//        insert db
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //getAll
    public List<NguoiDung> getAllNguoiDung() {
//        get all data for to list
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung ee = new NguoiDung();
            ee.setUserName(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setPhone(c.getString(2));
            ee.setHoTen(c.getString(3));
//            get data add list
            dsNguoiDung.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;
    }

    //update
    public int updateNguoiDung(NguoiDung nd) {
//        update nguoi dung according to the username
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoTen());
//        update for to database
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //  change Password
    public int changePasswordNguoiDung(NguoiDung nd) {
//        update Password according to the username
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //  update info for to nguoi dung
    public int updateInfoNguoiDung(String username, String phone, String name) {
        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("hoten", name);

        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{username});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //    delete    account
    public int deleteNguoiDungByID(String username) {
        int result = db.delete(TABLE_NAME, "username=?", new String[]{username});

        if (result == 0)
            return -1;
        return 1;
    }

    //check login
    public int checkLogin(String username, String password) {
        int result = 0;
//        get all data for to list
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung ee = new NguoiDung();
            ee.setUserName(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setPhone(c.getString(2));
            ee.setHoTen(c.getString(3));
            if (username.equals(c.getString(0))
                    && password.equals(c.getString(1))){
                c.close();
//                Log.e("-kiem tra login -------" , "----------thanh cong roi nha");
                return 1;
            }
//            get data add list
            dsNguoiDung.add(ee);
            Log.d("\tLogin //=====", ee.toString());
            c.moveToNext();
        }
        c.close();


//        int result = db.delete(TABLE_NAME, "username=? AND password=?", new String[]{username, password});
        if (result == 0)
            return -1;
        return 1;
    }
}
