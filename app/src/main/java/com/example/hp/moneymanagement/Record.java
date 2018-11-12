package com.example.hp.moneymanagement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    int id;
    String category;
    int money;
    String date;
    Record(int id,String category,int money,String date)
    {
        this.id=id;
        this.category = category;
        this.money = money;
        this.date = date;
    }
    Record()
    {

    }
    public int getId()
    {
      return id;
    }
    public String getCategory()
    {
        return category;
    }
    public int getMoney(){
        return money;
    }

    public String getDate() {
        return date;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
