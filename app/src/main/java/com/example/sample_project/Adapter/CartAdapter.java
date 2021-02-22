package com.example.sample_project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sample_project.DAO.HoaDonChiTietDAO;
import com.example.sample_project.Models.HoaDonChiTiet;
import com.example.sample_project.R;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    List<HoaDonChiTiet> arrHoaDonChiTiet;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    public CartAdapter(Activity context, List<HoaDonChiTiet> arrayHoaDonChiTiet) {
        super();
        this.context = context;
        this.arrHoaDonChiTiet = arrayHoaDonChiTiet;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return arrHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDonChiTiet.get(position);

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img_icon_hd;
        TextView txtMaSach;
        TextView txtSoLuong;
        TextView txtGiaBia;
        TextView txtThanhTien;
        ImageView imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.show_hdct, null);

            holder.img_icon_hd = (ImageView) convertView.findViewById(R.id.ivIcon_5);
            holder.txtMaSach = (TextView) convertView.findViewById(R.id.tv_MaSach_3);
            holder.txtSoLuong = (TextView) convertView.findViewById(R.id.tv_SoLuong_3);
            holder.txtGiaBia = (TextView) convertView.findViewById(R.id.tv_GiaBia_3);
            holder.txtThanhTien = (TextView)
                    convertView.findViewById(R.id.tv_ThanhTien_3);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.iv_Delete_5);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    hoaDonChiTietDAO.deleteHoaDonChiTietByID(String.valueOf(arrHoaDonChiTiet.get(position).getMaHDCT()));
                    arrHoaDonChiTiet.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HoaDonChiTiet _entry = (HoaDonChiTiet) arrHoaDonChiTiet.get(position);
        holder.img_icon_hd.setImageResource(R.drawable.hdicon);
        holder.txtMaSach.setText("Mã sách: " + _entry.getSach().getMaSach());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuongMua());
        holder.txtGiaBia.setText("Giá bìa: " + _entry.getSach().getGiaBia() + " vnd");
        holder.txtThanhTien.setText("Thành tiền: " + _entry.getSoLuongMua() * _entry.getSach().getGiaBia() + " vnd");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDonChiTiet> items) {
        this.arrHoaDonChiTiet = items;
        notifyDataSetChanged();
    }
}
