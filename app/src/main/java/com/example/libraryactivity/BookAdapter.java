package com.example.libraryactivity;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookListHolder>{

    private Context context;
    private ArrayList<Bookdetails> bookdetails;
    private BookOnClickListner bookOnClickListner;
    public BookAdapter(Context context, ArrayList<Bookdetails> bookdetails){
        this.context = context;
        this.bookdetails = bookdetails;
    }
    public void setBookOnClickListner(BookOnClickListner bookOnClickListner){
        this.bookOnClickListner = bookOnClickListner;
    }
    @NonNull
    @Override
    public BookListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_cellview,viewGroup,false);
        BookListHolder holder = new BookListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookListHolder bookListHolder, int i) {

        final Bookdetails data = bookdetails.get(i);

        bookListHolder.bookid.setText(String.valueOf(data.getId()));
        bookListHolder.bookname.setText(data.getName());

        bookListHolder.bookname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bookOnClickListner != null){
                    bookOnClickListner.showdata(data);
                }
            }
        });
        bookListHolder.bookid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bookOnClickListner != null){
                    bookOnClickListner.showdata(data);
                }
            }
        });
        bookListHolder.ibedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bookOnClickListner != null){
                    bookOnClickListner.updatedata(data);
                }
            }
        });
        bookListHolder.ibdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bookOnClickListner != null){
                    bookOnClickListner.deletedata(data);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookdetails.size();
    }

    //class book holder
    class BookListHolder extends RecyclerView.ViewHolder {
        TextView bookname,bookid;
        ImageButton ibedit,ibdelete;

        public BookListHolder(@NonNull View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.tv_bookname);
            bookid = itemView.findViewById(R.id.tv_bookid);
            ibdelete = itemView.findViewById(R.id.btn_delete);
            ibedit = itemView.findViewById(R.id.btn_edit);

        }
    }
    public interface BookOnClickListner{
        void showdata(Bookdetails bookdetails);
        void updatedata(Bookdetails bookdetails);
        void deletedata(Bookdetails bookdetails);
    }
}
