package com.example.hp.moneymanagement;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
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
import java.util.concurrent.ThreadPoolExecutor;

import static android.app.ProgressDialog.show;

public class Add extends Activity {
    MySQLiteHandler database;
    EditText edittext;
    Calendar myCalendar;
    String myFormat;
    SimpleDateFormat sdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        database = new MySQLiteHandler(this);
        myCalendar = Calendar.getInstance();
        myFormat = "yyyy-MM-dd"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        edittext= (EditText) findViewById(R.id.editText);
        edittext.setText("Today");
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

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated methodadd stub
                new DatePickerDialog(Add.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        myFormat = "yyyy-MM-dd"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
    public void addi(Record record)
    {
        database = new MySQLiteHandler(this);
        database.addRecord(record);
    }
    public void onDate(View view){

    }

    public void onClick(View view)
    {
        Record record = new Record();
        EditText money = (EditText) findViewById(R.id.editText2);
        if(money.getText().toString().compareTo("")==0){
            Toast.makeText(this,"AMOUNT CANNOT BE EMPTY",Toast.LENGTH_LONG).show();
            return;
        }
        Spinner s = (Spinner) findViewById(R.id.spinner);
        String category = s.getSelectedItem().toString();
        record.setMoney(Integer.parseInt(money.getText().toString()));
        record.setCategory(category);
        Log.d("error",myCalendar.getTime().toString());
        if(myCalendar.getTime()==null){
            Toast.makeText(this,"PLEASE PICK UP THE DATE",Toast.LENGTH_LONG).show();
            return;
        }
        record.setDate(sdf.format(myCalendar.getTime()));
        Log.d("category in add",category+record.getCategory());
        addi(record);
        Intent intent = new Intent(Add.this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Add.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void onClose(View view){
        Intent intent = new Intent(Add.this,MainActivity.class);
        startActivity(intent);
    }

}
