package com.example.pocket_card.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocket_card.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.image_doc);
    }
}
