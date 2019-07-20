package com.example.libraryactivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity implements BookAdapter.BookOnClickListner {

    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        recyclerView = findViewById(R.id.rc_booklist);

        recyclerView.setLayoutManager(new GridLayoutManager(BookListActivity.this, 3));

        databaseHelper = new DatabaseHelper(BookListActivity.this);

        FloatingActionButton insertdata = findViewById(R.id.fab_add);
        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertdata = new Intent(BookListActivity.this, MainActivity.class);
                startActivityForResult(insertdata, 1);
            }
        });
        setdatatolist();

    }

    private void setdatatolist() {

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ArrayList<Bookdetails> bookdetailsArrayList = databaseHelper.getAllDatabase(database);

        BookAdapter adapter = new BookAdapter(BookListActivity.this, bookdetailsArrayList);
        adapter.setBookOnClickListner(BookListActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showdata(Bookdetails bookdetails) {
        Intent detailintent = new Intent(BookListActivity.this, BookDetailActivity.class);
        detailintent.putExtra("BOOK_DETAIL", bookdetails);
        startActivity(detailintent);

    }

    @Override
    public void updatedata(Bookdetails bookdetails) {
        Intent updateIntent = new Intent(BookListActivity.this, MainActivity.class);
        updateIntent.putExtra("BOOK_DETAIL", bookdetails);
        startActivityForResult(updateIntent, 0);

    }

    @Override
    public void deletedata(Bookdetails bookdetails) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        databaseHelper.deleteData(bookdetails, database);
        setdatatolist();

    }

    private void insertdatatodatabase(Bookdetails bookdetails) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        databaseHelper.setInfoToDatabase(bookdetails, database);
        setdatatolist();
    }

    private void updatedatatodatabase(Bookdetails bookdetails) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        databaseHelper.updateData(bookdetails, database);
        setdatatolist();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Bookdetails updatevalue = (Bookdetails) data.getExtras().getSerializable("BOOK_DETAIL");
            switch (requestCode) {
                case 0:
                    updatedatatodatabase((updatevalue));
                    break;
                case 1:
                    insertdatatodatabase(updatevalue);
                    break;
            }
        } else {
            Snackbar.make(getCurrentFocus(), "User Canceled", Snackbar.LENGTH_SHORT).show();
        }
    }
}
