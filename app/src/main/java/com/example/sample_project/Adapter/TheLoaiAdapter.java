package com.example.sample_project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sample_project.DAO.TheLoaiDAO;
import com.example.sample_project.Models.TheLoai;
import com.example.sample_project.R;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    List<TheLoai> arrTheLoai;
    public Activity context;
    public LayoutInflater inflater;
    TheLoaiDAO theLoaiDAO;

    public TheLoaiAdapter(Activity context, List<TheLoai> arrayTheLoai) {
        super();
        this.context = context;
        this.arrTheLoai = arrayTheLoai;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new TheLoaiDAO(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.show_the_loai, null);

            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon2);
            holder.txtMaTheLoai = (TextView)
                    convertView.findViewById(R.id.tvMaTheLoai);
            holder.txtTenTheLoai = (TextView)
                    convertView.findViewById(R.id.tvTenTheLoai);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    theLoaiDAO.deleteTheLoaiByID(arrTheLoai.get(position).getMaTheLoai());
                    arrTheLoai.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TheLoai _entry = (TheLoai) arrTheLoai.get(position);
        holder.img.setImageResource(R.drawable.cateicon);
        holder.txtMaTheLoai.setText(_entry.getMaTheLoai());
        holder.txtTenTheLoai.setText(_entry.getTenTheLoai());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<TheLoai> items) {
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }
}

