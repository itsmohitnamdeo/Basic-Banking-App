package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(2345678901,'Mohit Namdeo',582.67,'namdeomohit379@gmail.com','XXXXXXXXXXXX2341','BCA98765432')");
        db.execSQL("insert into user_table values(3456789012,'MSDhoni',1359.56,'MSDhoni168@gmail.com','XXXXXXXXXXXX3412','CAB87654321')");
        db.execSQL("insert into user_table values(4567890123,'Sakshi',1500.01,'Sakshi509@gmail.com','XXXXXXXXXXXX4123','ABC76543210')");
        db.execSQL("insert into user_table values(5678901234,'Soumya',2603.48,'Soumya123@gmail.com','XXXXXXXXXXXX2345','BCA65432109')");
        db.execSQL("insert into user_table values(6789012345,'Aakanksha',945.16,'aakanksha@gmail.com','XXXXXXXXXXXX3452','CAB54321098')");
        db.execSQL("insert into user_table values(7890123456,'Atif Aslam',5936.00,'AtifAslam1122@gmail.com','XXXXXXXXXXXX4523','ABC43210987')");
        db.execSQL("insert into user_table values(8901234567,'Arijit singh',857.22,'Arijit23@gmail.com','XXXXXXXXXXXX5234','BCA32109876')");
        db.execSQL("insert into user_table values(9012345678,'Disha',4398.46,'Disha787@gmail.com','XXXXXXXXXXXX3456','CAB21098765')");
        db.execSQL("insert into user_table values(1234567809,'Shraddha',273.90,'shraddha318@gmail.com','XXXXXXXXXXXX4563','ABC10987654')");
        db.execSQL("insert into user_table values(1234567890,'Naman',9472.00,'Naman123@gmail.com','XXXXXXXXXXXX1234','ABC09876543')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
