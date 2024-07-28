package com.example.pocket_card.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocket_card.ModelClass.ImageObject;
import com.example.pocket_card.R;
import com.example.pocket_card.ViewHolder.ImageViewHolder;

import java.util.List;

public class Image_Adapter extends RecyclerView.Adapter<ImageViewHolder> {

    List<ImageObject> objectList;
    Context context;

    public Image_Adapter(List<ImageObject> objectList, Context context) {
        this.objectList = objectList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        ImageObject img = objectList.get(position);

    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
