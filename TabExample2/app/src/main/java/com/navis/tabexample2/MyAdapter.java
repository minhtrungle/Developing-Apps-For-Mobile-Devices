package com.navis.tabexample2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    LayoutInflater layoutInflater = null;
    LinkedList<String> listItemData = null;
    LinkedList<String> listItemTitle = null;
    LinkedList<String> listItemThumb = null;
    MyAdapter(Context context, LinkedList<String> listTitle, LinkedList<String> listData, LinkedList<String> listThumb)
    {
        layoutInflater = LayoutInflater.from(context);
        listItemData = listData;
        listItemTitle = listTitle;
        listItemThumb = listThumb;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.itemview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String itemText = listItemData.get(position);
        String itemTitle = listItemTitle.get(position);
        String url = listItemThumb.get(position);
        holder.textItem.setText(itemText);
        holder.textTitle.setText(itemTitle);
        RoundedCornersTransformation transformation = new RoundedCornersTransformation(5, 5);
        Picasso.get().load(url).transform(transformation).into(holder.imageThumb);
    }

    @Override
    public int getItemCount() {
        return listItemData.size();
    }
}
