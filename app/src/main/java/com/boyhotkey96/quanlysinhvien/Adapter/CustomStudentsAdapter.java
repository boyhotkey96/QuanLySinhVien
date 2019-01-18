package com.boyhotkey96.quanlysinhvien.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.boyhotkey96.quanlysinhvien.DTO.Students;
import com.boyhotkey96.quanlysinhvien.R;

import java.util.List;

/**
 * Created by boyhotkey96 on 18/08/2017.
 */

public class CustomStudentsAdapter extends ArrayAdapter<Students> {

    Activity activity;
    int resource;
    List<Students> objects;

    public CustomStudentsAdapter(Activity activity, int resource, List<Students> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        //View view = inflater.inflate(resource, parent, false);
        View view = inflater.inflate(R.layout.listitemsv, null, true);
        TextView tvsv0 = (TextView) view.findViewById(R.id.tvsv0);
        TextView tvsv1 = (TextView) view.findViewById(R.id.tvsv1);
        TextView tvsv2 = (TextView) view.findViewById(R.id.tvsv2);

        Students i2 = objects.get(position);

        tvsv0.setText(i2.get_id() + "");
        tvsv1.setText(i2.getTenSinhVien());
        tvsv2.setText(i2.getNgaySinh());

        return view;
    }
}
