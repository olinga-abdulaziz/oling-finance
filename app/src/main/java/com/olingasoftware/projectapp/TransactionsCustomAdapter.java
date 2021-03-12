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

public class TransactionsCustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<TransHolder> data;//modify here

    public TransactionsCustomAdapter(Context mContext, ArrayList<TransHolder> data) {
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
            convertView = inflater.inflate(R.layout.single_trans_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.accName = (TextView) convertView.findViewById(R.id.transName);
            viewHolder.depositAmount = (TextView) convertView.findViewById(R.id.transDepAmount);
            viewHolder.withdrawlAmount = (TextView) convertView.findViewById(R.id.transWithdAmount);
            viewHolder.accPhone = (TextView) convertView.findViewById(R.id.transPhone);
            viewHolder.time = (TextView) convertView.findViewById(R.id.transPhone);
            //Up to here
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        TransHolder person = data.get(position);//modify here
        viewHolder.accName.setText(person.getAccName());//modify here
        viewHolder.depositAmount.setText(person.getDepositAmount());//modify here
        viewHolder.withdrawlAmount.setText(person.getWithdrawlAmount());//modify here
        viewHolder.accPhone.setText(person.getAccPhone());//modify here
        viewHolder.time.setText(person.getTime());//modify here
        return convertView;

    }
    static class ViewHolder {
        TextView accName;
        TextView depositAmount;
        TextView withdrawlAmount;
        TextView accPhone;
        TextView time;


    }

}
