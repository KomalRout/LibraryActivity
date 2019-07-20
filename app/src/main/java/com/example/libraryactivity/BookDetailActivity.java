package com.example.libraryactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BookDetailActivity extends AppCompatActivity {
    TextView bookid,bookname,bookauthor,bookjournel,bookdesc,bookabs,bookyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookid = findViewById(R.id.txt_Bookid);
        bookname = findViewById(R.id.txt_Bookname);
        bookauthor = findViewById(R.id.txt_Bookauthor);
        bookjournel = findViewById(R.id.txt_Bookjournel);
        bookdesc = findViewById(R.id.txt_Bookdescription);
        bookabs = findViewById(R.id.txt_Bookabs);
        bookyear = findViewById(R.id.txt_Bookyear);

        Bundle data = getIntent().getExtras();

        Bookdetails bookinfo = (Bookdetails) data.getSerializable("BOOK_DETAIL");
        bookid.setText(String.valueOf(bookinfo.getId()));
        bookname.setText(bookinfo.getName());
        bookyear.setText(bookinfo.getYear());
        bookdesc.setText(bookinfo.getSub());
        bookjournel.setText(bookinfo.getJournel());
        bookauthor.setText(bookinfo.getAuthor());
        bookabs.setText(bookinfo.getAbs());




    }
}
