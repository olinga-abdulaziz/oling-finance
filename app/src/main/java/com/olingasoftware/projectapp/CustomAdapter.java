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

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<GroupTransHolder> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<GroupTransHolder> data) {
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
            convertView = inflater.inflate(R.layout.single_group_transaction, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.tvnames = (TextView) convertView.findViewById(R.id.edtamounDep);
            viewHolder.amoutWithd = (TextView) convertView.findViewById(R.id.edtamounWithdraw);
            viewHolder.tvincome = (TextView) convertView.findViewById(R.id.edtincome);
            viewHolder.amoutWithd = (TextView) convertView.findViewById(R.id.edtemail);
            viewHolder.tvtime = (TextView) convertView.findViewById(R.id.edttime);

            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        GroupTransHolder person = data.get(position);//modify here
        viewHolder.tvnames.setText(person.getAmountDeposit());//modify here
        viewHolder.amoutWithd.setText(person.getAmountWithdraw());//modify here
        viewHolder.tvincome.setText(person.getSourceIncome());//modify here
        viewHolder.useremail.setText(person.getUserAdmin());//modify here
        viewHolder.tvtime.setText(person.getTime());//modify here

        return convertView;

    }
    static class ViewHolder {
        TextView tvnames;
        TextView amoutWithd;
        TextView tvincome;//These don't have to be same names as the Id's
        TextView useremail;//These don't have to be same names as the Id's
        TextView tvtime;//These don't have to be same names as the Id's

    }

}
