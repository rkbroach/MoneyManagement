package com.example.hp.moneymanagement;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchListView extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    TextView t1,t2,t3;
    public SearchListView(Context context){
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(Data.rsearch == null)
            return 0;
        return Data.rsearch.size();
    }

    @Override
    public Object getItem(int i) {
        return Data.rsearch.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.search_listview,null);
        t1 = (TextView) view.findViewById(R.id.textView9);
        t2 = (TextView) view.findViewById(R.id.textView11);
        t3 = (TextView) view.findViewById(R.id.textView10);
        Log.d("date",Data.rsearch.get(i).date);
        t1.setText(Data.rsearch.get(i).getDate());
        t2.setText(Data.rsearch.get(i).getCategory());
        t3.setText(Integer.toString(Data.rsearch.get(i).getMoney()));
        return view;
    }
}
