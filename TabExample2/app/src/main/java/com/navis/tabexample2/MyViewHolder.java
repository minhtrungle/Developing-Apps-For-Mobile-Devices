package com.navis.tabexample2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textItem = null;
    public TextView textTitle = null;
    public ImageView imageThumb = null;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        textItem = itemView.findViewById(R.id.textItem);
        textTitle = itemView.findViewById(R.id.textTitle);
        imageThumb = itemView.findViewById(R.id.imageThumb);
    }
}
