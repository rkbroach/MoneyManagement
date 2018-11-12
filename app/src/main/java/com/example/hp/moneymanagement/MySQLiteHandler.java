package com.example.hp.moneymanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "money1.db";
    private static final String TABLE_COMPUTER = "money";
    private static final String COLOUMN_ID = "id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_MONEY = "money";
    private static final String COLUMN_DATE = "date";

    String CREATE_MONEY_TABLE = "CREATE TABLE " + TABLE_COMPUTER + "(" + COLOUMN_ID +
            " INTEGER PRIMARY KEY, " + COLUMN_CATEGORY + " TEXT, "+
            COLUMN_MONEY +  "  INTEGER, "+ COLUMN_DATE +" date " + ")";
    public MySQLiteHandler(Context context)
    {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MONEY_TABLE);
    }

    public void addRecord(Record record)
    {
        SQLiteDatabase database = MySQLiteHandler.this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY,record.getCategory());
        values.put(COLUMN_MONEY,record.getMoney());
        values.put(COLUMN_DATE,record.getDate());
        try {
            database.insert(TABLE_COMPUTER, null, values);
        }
        catch (Exception e){
            Log.getStackTraceString(e);
        }
        database.close();
    }

    public Record getRecord(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor  = database.query(TABLE_COMPUTER,new String[]{COLOUMN_ID,COLUMN_CATEGORY,COLUMN_MONEY,COLUMN_DATE},COLOUMN_ID + "=?", new String[] {String.valueOf(id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Record record = new Record(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)),cursor.getString(3));
        return record;
    }

    public List<Record> getAllRecord(){
        List<Record> records = new ArrayList<Record>();
        String selectAllQuery = "SELECT * FROM " + TABLE_COMPUTER + " ORDER BY date("+COLUMN_DATE+")"+" DESC";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectAllQuery,null);
        if(cursor.moveToFirst()){
            do{
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setMoney(cursor.getInt(2));
                record.setCategory(cursor.getString(1));
                record.setDate(cursor.getString(3));
                records.add(record);
            }
            while (cursor.moveToNext());
        }
        return records;
    }


    public int updateRecord(Record record){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY,record.getCategory());
        values.put(COLUMN_MONEY,record.getMoney());
        values.put(COLUMN_DATE,record.getDate());
        return database.update(TABLE_COMPUTER,values,COLOUMN_ID + " = ?", new String[]{String.valueOf(record.getId())});
    }
    public void deleteRecord(Record record){
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            database.delete(TABLE_COMPUTER, COLOUMN_ID + " = ?", new String[]{String.valueOf(record.getId())});
        }
        catch (Exception e){
            Log.getStackTraceString(e);
        }
        database.close();
    }
    public int getNoofRecords(){
        String computerCountQuery = "SELECT * FROM " + TABLE_COMPUTER + " ORDER BY strftime('%s',"+COLUMN_DATE+")"+ " DESC";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(computerCountQuery,null);
        return cursor.getCount();
    }
}
