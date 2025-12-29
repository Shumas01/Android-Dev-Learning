package com.example.bookvault;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;
    private OnItemClickListener listener;

    // Interface to allow MainActivity to handle clicks
    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public BookAdapter(List<Book> bookList, OnItemClickListener listener) {
        this.bookList = bookList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Uses the standard Android list item layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.textTitle.setText(book.getTitle());
        holder.textSubtitle.setText("Author: " + book.getAuthor());

        // When the row is clicked, trigger the listener
        holder.itemView.setOnClickListener(v -> listener.onItemClick(book));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textSubtitle;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            // These IDs exist in android.R.layout.simple_list_item_2
            textTitle = itemView.findViewById(android.R.id.text1);
            textSubtitle = itemView.findViewById(android.R.id.text2);
        }
    }
}