package com.example.jboeser.seriestoevoegen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by j.boeser on 24-2-2017.
 */

public class ItemAdapter extends ArrayAdapter<ListItem>{

    private LayoutInflater inflater;

    public ItemAdapter (Context context, int resource, List<ListItem> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.row_item, parent, false);
        ListItem item = getItem(position);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView season = (TextView) convertView.findViewById(R.id.season);
        title.setText(item.getTitle());
        season.setText(item.getSeason());
        return convertView;
    }

    }
