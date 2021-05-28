package com.example.filterapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filterapp.FiltersListFragment;
import com.example.filterapp.Interface.FiltersListFragmentListener;
import com.example.filterapp.R;
import com.zomato.photofilters.utils.ThumbnailItem;

import java.util.List;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.MyViewHolder> {

    Context context;
    List<ThumbnailItem> thumbnailItems;
    FiltersListFragmentListener listener;
    int selectedIndex = 0;

    public ThumbnailAdapter(Context context,List<ThumbnailItem> thumbnailItems, FiltersListFragmentListener listener) {
        this.context = context;
        this.thumbnailItems = thumbnailItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ThumbnailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.thumbnail_item,parent,false);
       return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailAdapter.MyViewHolder holder, final int position) {
        final ThumbnailItem thumbnailItem = thumbnailItems.get(position);

        holder.thumbnail.setImageBitmap(thumbnailItem.image);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFilterSelected(thumbnailItem.filter);
                selectedIndex = position;
                notifyDataSetChanged();
            }
        });

        holder.filter_name.setText(thumbnailItem.filterName);

        if(selectedIndex == position){
            holder.filter_name.setTextColor(ContextCompat.getColor(context,R.color.selected_filter));
        }else {
            holder.filter_name.setTextColor(ContextCompat.getColor(context,R.color.normal_filter));
        }
    }

    @Override
    public int getItemCount() {
        return thumbnailItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView filter_name;
        ImageView thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            filter_name = itemView.findViewById(R.id.filter_name);

        }
    }
}
