package com.example.libraryactivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_headline;
    EditText bid,bname,bauthor,babs,bjournel,byear,bsub;
    Button btn_insert;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_headline=findViewById(R.id.text_headline);
        bid = findViewById(R.id.bid);
        bname = findViewById(R.id.bname);
        bauthor = findViewById(R.id.bauthor);
        babs = findViewById(R.id.babs);
        bjournel = findViewById(R.id.bjournel);
        byear = findViewById(R.id.byear);
        bsub = findViewById(R.id.bdesc);
        btn_insert = findViewById(R.id.btn_insert);
        databaseHelper = new DatabaseHelper(MainActivity.this);

        Bundle data = getIntent().getExtras();
        if(data != null){
            Bookdetails booklist = (Bookdetails)data.getSerializable("BOOK_DETAIL");
            if(booklist != null){
                bid.setText(String.valueOf(booklist.getId()));
                bname.setText(booklist.getName());
                bauthor.setText(booklist.getAuthor());
                babs.setText(booklist.getAbs());
                bjournel.setText(booklist.getJournel());
                byear.setText(booklist.getYear());
                bsub.setText(booklist.getSub());

                bid.setEnabled(false);
            }
        }
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(bid.getText().toString());
                String name = bname.getText().toString();
                String author = bauthor.getText().toString();
                String abs = babs.getText().toString();
                String journel = bjournel.getText().toString();
                String year = byear.getText().toString();
                String desc = bsub.getText().toString();

                Bookdetails bookinfo = new Bookdetails();
                bookinfo.setId(id);
                bookinfo.setName(name);
                bookinfo.setAbs(abs);
                bookinfo.setAuthor(author);
                bookinfo.setSub(desc);
                bookinfo.setJournel(journel);
                bookinfo.setYear(year);

                bid.setText("");
                bname.setText("");
                bauthor.setText("");
                babs.setText("");
                bjournel.setText("");
                byear.setText("");
                bsub.setText("");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("BOOK_DETAIL", bookinfo);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent cancelintent = new Intent();
        setResult(Activity.RESULT_CANCELED,cancelintent);
        finish();
    }
}
