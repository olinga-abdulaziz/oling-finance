package com.olingasoftware.projectapp;

//CustomAdapter
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectGroupCustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<GroupHolder> data;//modify here

    public SelectGroupCustomAdapter(Context mContext, ArrayList<GroupHolder> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.single_group, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.tvnames = (TextView) convertView.findViewById(R.id.txtGroupListName);
            viewHolder.tvgroupid = (TextView) convertView.findViewById(R.id.txtGroupListId);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        GroupHolder person = data.get(position);//modify here
        viewHolder.tvnames.setText(person.getGroupName());//modify here
        viewHolder.tvgroupid.setText(person.getGroupId());//modify here
        return convertView;

    }
    static class ViewHolder {
        TextView tvnames;
        TextView tvgroupid;
    }

}
