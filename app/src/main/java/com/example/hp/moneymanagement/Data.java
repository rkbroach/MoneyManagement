package com.example.hp.moneymanagement;

import java.util.Date;
import java.util.List;

public class Data {
    static int income=0;
    static int balance=0;
    static int expanse=0;
    static String com="";
    static List<Integer> id;
    static List<Integer> values;
    static List<String> category;
    static List<String> date;
    static List<Integer> search;
    static List<Record> rsearch;
    static void Update()
    {
        income=0;
        expanse=0;
        for(int i=0;i<category.size();i++)
        {
            if(category.get(i).compareTo("Salary")==0){
                income+=values.get(i);
            }
            else {
                expanse+=values.get(i);
            }
        }
        balance = income - expanse;
    }
}
