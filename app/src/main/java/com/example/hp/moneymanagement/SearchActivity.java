package com.example.hp.moneymanagement;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchActivity extends AppCompatActivity {
    MySQLiteHandler m;
    SearchListView adaptor;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageButton btn = (ImageButton) findViewById(R.id.imageButton2);
        adaptor = new SearchListView(this);
        final Intent intent = new Intent(this,Edit.class);
        listView = findViewById(R.id.lias);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("value",i);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        Data.search = new ArrayList<Integer>();
        Data.rsearch = new ArrayList<Record>();
        m = new MySQLiteHandler(this);

        final Intent intent1 = new Intent(this,MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent1);
                finish();
            }
        });

        final EditText editText =(EditText) findViewById(R.id.editText4);
        editText.setOnEditorActionListener(new EditText.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        Record record;
                        int y=-1;
                        int s = Integer.parseInt(editText.getText().toString());
                        if(editText.getText().toString().compareTo("")==0){
                            Toast.makeText(getBaseContext(),"ENTER THE TRANSACTION AMOUNT",Toast.LENGTH_LONG).show();
                        }
                        for(Integer i:Data.id){
                            record = m.getRecord(i);
                            if(record.getMoney()==s){
                                Data.rsearch.add(record);
                                y=i;
                            }
                        }
                        if(y==-1){
                            Toast.makeText(getBaseContext(),"NO TRANSACTION FOUND",Toast.LENGTH_LONG).show();
                        }
                        listView.setAdapter(adaptor);

                        return true; // consume.
                    }
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        final Intent intent1 = new Intent(this,MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
        finish();
    }
}
