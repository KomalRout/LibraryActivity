package com.example.libraryactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"bookdetails.db", factory,3);
    }
    public DatabaseHelper(Context context){
        super(context,"bookdetails.db",null,3);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Bookdetails.CREATE_TABLE_QUERRY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Bookdetails.TABLE_NAME);
        db.execSQL(Bookdetails.CREATE_TABLE_QUERRY);
    }
    public void setInfoToDatabase(Bookdetails bookdetails,SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Bookdetails.COL_BID,bookdetails.getId());
        contentValues.put(Bookdetails.COL_AUTHOR,bookdetails.getAuthor());
        contentValues.put(Bookdetails.COL_J,bookdetails.getJournel());
        contentValues.put(Bookdetails.COL_ABS,bookdetails.getAbs());
        contentValues.put(Bookdetails.COL_SUB,bookdetails.getSub());
        contentValues.put(Bookdetails.COL_YEAR,bookdetails.getYear());
        contentValues.put(Bookdetails.COL_BNAME,bookdetails.getName());

        database.insert(Bookdetails.TABLE_NAME,null, contentValues);
    }

    public ArrayList<Bookdetails> getAllDatabase(SQLiteDatabase sqLiteDatabase){
        ArrayList<Bookdetails> booklist = new ArrayList<>();

        String READ_QUERY = "SELECT * FROM " + Bookdetails.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(READ_QUERY,null);

        if(cursor.moveToFirst()){
            do{
                Bookdetails bookdetail = new Bookdetails();
                bookdetail.setId(cursor.getInt(cursor.getColumnIndex(Bookdetails.COL_BID)));
                bookdetail.setName(cursor.getString(cursor.getColumnIndex(Bookdetails.COL_BNAME)));
                bookdetail.setYear(cursor.getString(cursor.getColumnIndex(Bookdetails.COL_YEAR)));
                bookdetail.setJournel(cursor.getString(cursor.getColumnIndex(Bookdetails.COL_J)));
                bookdetail.setSub(cursor.getString(cursor.getColumnIndex(Bookdetails.COL_SUB)));
                bookdetail.setAbs(cursor.getString(cursor.getColumnIndex(Bookdetails.COL_ABS)));
                bookdetail.setAuthor(cursor.getString(cursor.getColumnIndex(Bookdetails.COL_AUTHOR)));

                booklist.add(bookdetail);

            }while(cursor.moveToNext());
        }
        cursor.close();
        return booklist;
    }
    public void updateData(Bookdetails bookdetails,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Bookdetails.COL_AUTHOR,bookdetails.getAuthor());
        contentValues.put(Bookdetails.COL_J,bookdetails.getJournel());
        contentValues.put(Bookdetails.COL_ABS,bookdetails.getAbs());
        contentValues.put(Bookdetails.COL_SUB,bookdetails.getSub());
        contentValues.put(Bookdetails.COL_YEAR,bookdetails.getYear());
        contentValues.put(Bookdetails.COL_BNAME,bookdetails.getName());

        sqLiteDatabase.update(Bookdetails.TABLE_NAME, contentValues,Bookdetails.COL_BID+ "=" + bookdetails.getId(),null);
    }
    public void deleteData(Bookdetails bookdetails,SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.delete(Bookdetails.TABLE_NAME,Bookdetails.COL_BID + "=" + bookdetails.getId(),null);
    }
}

