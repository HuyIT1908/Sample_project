package com.example.sample_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sample_project.R;

import java.util.List;

public class Grid_adapter extends BaseAdapter {
    private Context context;
    private List<String> name;
    private List<Integer> img;

    public Grid_adapter(Context context, List<String> name, List<Integer> img) {
        this.context = context;
        this.name = name;
        this.img = img;
    }

    @Override
    public int getCount() {
        return img.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Grid_main grid_main;
        if (convertView == null){
            grid_main = new Grid_main();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_show_main , null);

            grid_main.imageView = (ImageView) convertView.findViewById(R.id.imv_grid);
            grid_main.textView = (TextView) convertView.findViewById(R.id.tv_grid);

            convertView.setTag(grid_main);
        }else {
            grid_main = (Grid_main) convertView.getTag();
        }
        grid_main.imageView.setImageResource(img.get(position));
        grid_main.textView.setText(name.get(position));
        return convertView;
    }
    public static class Grid_main{
        ImageView imageView;
        TextView textView;
    }
}
