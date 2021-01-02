package com.example.storemydata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "Database";
    public static final String Table_Name = "Database_table";
    public static final String column1 = "ID";
    public static final String column2 = "FIRSTNAME";
    public static final String column3 = "SURNAME";
    public static final String column4 = "MARKS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, " +
                "SURNAME TEXT, MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Table_Name);
            onCreate(sqLiteDatabase);
    }

    public Boolean insertdata(String firstname, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column2, firstname);
        contentValues.put(column3, surname);
        contentValues.put(column4, marks);
        long result = db.insert(Table_Name,null,contentValues);

        if (result == -1){
            return false;
        }
            else {
                return true;
        }

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + Table_Name,null);
        return cursor;
    }

    public Boolean updatedata(String id,String firstname, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column1, id);
        contentValues.put(column2, firstname);
        contentValues.put(column3, surname);
        contentValues.put(column4, marks);
        db.update(Table_Name,contentValues,"ID = ?", new String[]{id});
        return true;
    }

    public Integer deletedata(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column1, id);
        return db.delete(Table_Name, "ID = ?", new String[]{id});
    }
}
