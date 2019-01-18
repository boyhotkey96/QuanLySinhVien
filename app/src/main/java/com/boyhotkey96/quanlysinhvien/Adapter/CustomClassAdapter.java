package com.boyhotkey96.quanlysinhvien.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.boyhotkey96.quanlysinhvien.DTO.Class;
import com.boyhotkey96.quanlysinhvien.R;

import java.util.List;

/**
 * Created by boyhotkey96 on 18/08/2017.
 */

public class CustomClassAdapter extends ArrayAdapter<Class> {

    Activity activity;
    int resource;
    List<Class> objects;

    public CustomClassAdapter(Activity activity, int resource, List<Class> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        //View view = inflater.inflate(resource, parent, false);
        View view = inflater.inflate(R.layout.list_item, null, true);
        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        TextView tv2 = (TextView) view.findViewById(R.id.tv2);
        TextView tv3 = (TextView) view.findViewById(R.id.tv3);

        Class i = objects.get(position);

        tv1.setText(i.get_id() + "");
        tv2.setText(i.getMaLop());
        tv3.setText(i.getTenLop());

        return view;
    }
}
