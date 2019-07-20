package com.example.libraryactivity;

import java.io.Serializable;
import java.security.PublicKey;

public class Bookdetails implements Serializable {


    public static final String COL_BID = "BOOK_ID";
    public static final String COL_BNAME = "BOOK_NAME";
    public static final String COL_AUTHOR = "BOOK_AUTHOR";
    public static final String COL_ABS = "ABSTRACT";
    public static final String COL_YEAR = "YEAR";
    public static final String COL_SUB = "BOOK_SUBJECT";
    public static final String COL_J = "JOURNEL";

    public static final String TABLE_NAME = "BOOK_DETAILS";

    public static final String CREATE_TABLE_QUERRY="CREATE TABLE " + TABLE_NAME + "(" + COL_BID + " INTEGER PRIMARY KEY," +
            COL_BNAME + " TEXT," + COL_AUTHOR + " TEXT," + COL_ABS + " TEXT," + COL_YEAR + " TEXT," +
            COL_SUB + " TEXT," + COL_J + " TEXT)";


    private int id;
    private String name,author,abs,journel,year,sub;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getJournel() {
        return journel;
    }

    public void setJournel(String journel) {
        this.journel = journel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
