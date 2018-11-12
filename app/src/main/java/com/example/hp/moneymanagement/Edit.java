package com.example.hp.moneymanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Edit extends AppCompatActivity {
    EditText editText,editText1;
    MySQLiteHandler database;
    Spinner s;
    Calendar myCalendar;
    String myFormat;
    SimpleDateFormat sdf;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        database = new MySQLiteHandler(this);
        Intent intent = getIntent();
        i = intent.getIntExtra("value",-1);
        if(i==-1){
            Log.d("Edit","problem");
        }
        editText = (EditText) findViewById(R.id.editText);
        editText1 = (EditText) findViewById(R.id.editText2);
        s = (Spinner) findViewById(R.id.spinner);
        editText.setText(Data.date.get(i));
        editText1.setText(Data.values.get(i).toString());
        String[] array = {"Salary","Clothing","Entertainment","Food","Fuel","Health"};
        int x=0;
        for(int y=0;y<array.length;y++)
        {
            if(array[y].compareTo(Data.category.get(i))==0){
                x=y;
                break;
            }
        }
        s.setSelection(x);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated methodadd stub
                String d = editText.getText().toString();
                myCalendar = Calendar.getInstance();
                myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat, Locale.US);
                try {
                    myCalendar.setTime(sdf1.parse(myFormat));
                }
                catch(Exception e){
                    Log.getStackTraceString(e);
                }
                new DatePickerDialog(Edit.this, date, myCalendar
                        .get(myCalendar.YEAR), myCalendar.get(myCalendar.MONTH),
                        myCalendar.get(myCalendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        myFormat = "yyyy-MM-dd"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        Data.date.set(i,sdf.format(myCalendar.getTime()));
        editText.setText(sdf.format(myCalendar.getTime()));
    }
    public void addi(Record record){
        database = new MySQLiteHandler(this);
        database.updateRecord(record);
    }
    public void delei(Record record)
    {
        database = new MySQLiteHandler(this);
        database.deleteRecord(record);
    }
    public void onClick(View view)
    {
        Record record = new Record();
        EditText money = (EditText) findViewById(R.id.editText2);
        if(money.getText().toString().compareTo("")==0){
            Toast.makeText(this,"AMOUNT CANNOT BE EMPTY",Toast.LENGTH_LONG).show();
            return;
        }
        if(Data.date.get(i)==null){
            Toast.makeText(this,"PICK UP A DATE",Toast.LENGTH_LONG).show();
            return;
        }
        s = (Spinner) findViewById(R.id.spinner);
        String category = s.getSelectedItem().toString();
        record.setMoney(Integer.parseInt(money.getText().toString()));
        record.setCategory(category);
        record.setDate(Data.date.get(i));
        record.setId(Data.id.get(i));
        Log.d("category in add",category+record.getCategory());
        addi(record);
        Intent intent = new Intent(Edit.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Edit.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void onClose(View view){
        Intent intent = new Intent(Edit.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    public void onDelete(View view){
        Record record = new Record();
        EditText money = (EditText) findViewById(R.id.editText2);
        if(money.getText().toString().compareTo("")==0){
            Toast.makeText(this,"AMOUNT CANNOT BE EMPTY",Toast.LENGTH_LONG).show();
            return;
        }
        if(Data.date.get(i)==null){
            Toast.makeText(this,"PICK UP A DATE",Toast.LENGTH_LONG).show();
            return;
        }
        record.setId(Data.id.get(i));
        String category = s.getSelectedItem().toString();
        record.setMoney(Integer.parseInt(money.getText().toString()));
        record.setCategory(category);
        record.setDate(Data.date.get(i));
        delei(record);
        Intent intent = new Intent(Edit.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
