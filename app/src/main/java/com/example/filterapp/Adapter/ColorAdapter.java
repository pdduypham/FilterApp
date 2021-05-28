package com.example.filterapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filterapp.R;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    Context context;
    List<Integer> colorList;
    ColorAdapterListener listener;

    public ColorAdapter(Context context, ColorAdapterListener listener) {
        this.context = context;
        this.colorList = getColorList();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.color_item,parent,false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.ColorViewHolder holder, int position) {
        holder.color_section.setCardBackgroundColor(colorList.get(position));
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder {

        public CardView color_section;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            color_section = itemView.findViewById(R.id.color_section);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onColorSelected(colorList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface ColorAdapterListener{
        void onColorSelected(int color);
    }

    private List<Integer> getColorList() {
        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#000000"));
        colorList.add(Color.parseColor("#ffffff"));
        colorList.add(Color.parseColor("#696969"));
        colorList.add(Color.parseColor("#bada55"));
        colorList.add(Color.parseColor("#7fe5f0"));
        colorList.add(Color.parseColor("#ff0000"));
        colorList.add(Color.parseColor("#ff80ed"));
        colorList.add(Color.parseColor("#407294"));
        colorList.add(Color.parseColor("#0ff1ce"));
        colorList.add(Color.parseColor("#cbcba9"));
        colorList.add(Color.parseColor("#420420"));
        colorList.add(Color.parseColor("#133337"));
        colorList.add(Color.parseColor("#065535"));
        colorList.add(Color.parseColor("#c0c0c0"));
        colorList.add(Color.parseColor("#5ac18e"));
        colorList.add(Color.parseColor("#666666"));
        colorList.add(Color.parseColor("#dcedc1"));
        colorList.add(Color.parseColor("#f7347a"));
        colorList.add(Color.parseColor("#576675"));
        colorList.add(Color.parseColor("#ffc0cb"));
        colorList.add(Color.parseColor("#ffe4e1"));
        colorList.add(Color.parseColor("#008080"));
        colorList.add(Color.parseColor("#696966"));
        colorList.add(Color.parseColor("#ffd700"));
        colorList.add(Color.parseColor("#e6e6fa"));
        colorList.add(Color.parseColor("#ffa500"));
        colorList.add(Color.parseColor("#8a2be2"));
        colorList.add(Color.parseColor("#ff7373"));
        colorList.add(Color.parseColor("#00ffff"));
        colorList.add(Color.parseColor("#40e0d0"));
        colorList.add(Color.parseColor("#0000ff"));
        colorList.add(Color.parseColor("#f0f8ff"));
        colorList.add(Color.parseColor("#d3ffce"));
        colorList.add(Color.parseColor("#c6e2ff"));
        colorList.add(Color.parseColor("#b0e0e6"));
        colorList.add(Color.parseColor("#faebd7"));
        colorList.add(Color.parseColor("#fa8072"));
        colorList.add(Color.parseColor("#003366"));
        colorList.add(Color.parseColor("#ffff00"));
        colorList.add(Color.parseColor("#ffb6c1"));
        colorList.add(Color.parseColor("#800000"));
        colorList.add(Color.parseColor("#800080"));
        colorList.add(Color.parseColor("#f08080"));
        colorList.add(Color.parseColor("#7fffd4"));
        colorList.add(Color.parseColor("#c39797"));
        colorList.add(Color.parseColor("#fff68f"));
        colorList.add(Color.parseColor("#eeeeee"));
        colorList.add(Color.parseColor("#00ff00"));
        colorList.add(Color.parseColor("#cccccc"));
        colorList.add(Color.parseColor("#ffc3a0"));
        colorList.add(Color.parseColor("#20b2aa"));
        colorList.add(Color.parseColor("#ac25e2"));
        colorList.add(Color.parseColor("#333333"));
        colorList.add(Color.parseColor("#66cdaa"));
        colorList.add(Color.parseColor("#ff6666"));
        colorList.add(Color.parseColor("#ffdab9"));
        colorList.add(Color.parseColor("#ff00ff"));
        colorList.add(Color.parseColor("#ff7f50"));
        colorList.add(Color.parseColor("#c0d6e4"));
        colorList.add(Color.parseColor("#4ca3dd"));

        return colorList;
    }
}
