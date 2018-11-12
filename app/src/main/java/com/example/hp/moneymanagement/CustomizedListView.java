package com.example.hp.moneymanagement;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomizedListView extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public CustomizedListView(Context context){
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(Data.category == null)
            return 0;
        return Data.category.size();
    }

    @Override
    public Object getItem(int i) {
        return Data.category.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.customized_listview, null);
        TextView t1 = (TextView) view.findViewById(R.id.textView);
        TextView t2 = (TextView) view.findViewById(R.id.textView2);
        TextView t3 = (TextView) view.findViewById(R.id.textView3);
        t1.setText(Data.category.get(i));
        Log.d("category in adaptor", Data.category.get(i));
        String s = String.valueOf(Data.values.get(i));
        t2.setText(s);
        String str = Data.date.get(i);
        str = str.substring(5);
        //t3.setText(Data.date.get(i));
        t3.setText(str);
        if (Data.com.compareTo(Data.date.get(i))!=0){
            t3.setVisibility(View.VISIBLE);
        }
        else{
            t3.setVisibility(View.INVISIBLE);
        }
        switch (Data.category.get(i)){
            case "Salary":
                t2.setBackgroundColor(Color.parseColor("#41A3F5"));
                t1.setBackgroundColor(Color.parseColor("#41A3F5"));
                break;
            case "Clothing":
                t2.setBackgroundColor(Color.parseColor("#AC45BC"));
                t1.setBackgroundColor(Color.parseColor("#AC45BC"));
                break;
            case "Entertainment":
                t2.setBackgroundColor(Color.parseColor("#EA3F78"));
                t1.setBackgroundColor(Color.parseColor("#EA3F78"));
                break;
            case "Food":
                t2.setBackgroundColor(Color.parseColor("#ED524A"));
                t1.setBackgroundColor(Color.parseColor("#ED524A"));
                break;
            case "Fuel":
                t2.setBackgroundColor(Color.parseColor("#7C59C1"));
                t1.setBackgroundColor(Color.parseColor("#7C59C1"));
                break;
            case "Health":
                t2.setBackgroundColor(Color.parseColor("#5E6AC1"));
                t1.setBackgroundColor(Color.parseColor("#5E6AC1"));
                break;
        }

        t2.setTextColor(Color.WHITE);
        t1.setTextColor(Color.WHITE);
        Data.com=Data.date.get(i);
        return view;
    }

}
