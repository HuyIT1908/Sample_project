package com.example.sample_project.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sample_project.DAO.NguoiDungDAO;
import com.example.sample_project.Models.NguoiDung;
import com.example.sample_project.R;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> arrNguoiDung;
    public Activity context;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter(Activity context, List<NguoiDung> arrayNguoiDung) {
        super();
        this.context = context;
        this.arrNguoiDung = arrayNguoiDung;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.show_dss_user, null);

            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon2);
            holder.txtName = (TextView) convertView.findViewById(R.id.tvName);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo").setMessage("Bạn có chắc chắn muốn xóa người dùng này không ?");
                    builder.setPositiveButton("Xóa",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    nguoiDungDAO.deleteNguoiDungByID(arrNguoiDung.get(position).getUserName());
                                    arrNguoiDung.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Đã xóa người dùng thành công",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            convertView.setTag(holder);

        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        NguoiDung _entry = (NguoiDung) arrNguoiDung.get(position);

        if (position % 3 == 0) {

            holder.img.setImageResource(R.drawable.emone);
        } else if (position % 3 == 1) {

            holder.img.setImageResource(R.drawable.emtwo);
        } else {

            holder.img.setImageResource(R.drawable.emthree);
        }
        holder.txtName.setText(_entry.getHoTen());
        holder.txtPhone.setText(_entry.getPhone());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<NguoiDung> items){
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }
}
