package com.example.filterapp.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filterapp.R;

import java.util.ArrayList;
import java.util.List;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.FontViewHolder> {

    Context context;
    FontAdapterClickListener listener;
    List<String> fontList;

    int row_selected = -1;

    public FontAdapter(Context context, FontAdapterClickListener listener) {
        this.context = context;
        this.listener = listener;
        fontList = loadFontList();
    }

    private List<String> loadFontList() {
        List<String> result = new ArrayList<>();
        result.add("Chunk Five Print.otf");
        result.add("ChunkFive-Regular.otf");
        result.add("GreatVibes-Regular.otf");
        result.add("KaushanScript-Regular.otf");
        result.add("Lobster_1.3.otf");
        result.add("Quicksand-BoldItalic.otf");
        result.add("Quicksand-Italic.otf");
        result.add("Quicksand-Light.otf");
        result.add("Quicksand-LightItalic.otf");
        result.add("Quicksand-Regular.otf");
        result.add("Quicksand_Dash.otf");
        result.add("Shrift_Steamy.otf");

        return result;
    }

    @NonNull
    @Override
    public FontViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.font_item, parent, false);
        return new FontViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FontAdapter.FontViewHolder holder, int position) {
        if (row_selected == position) {
            holder.img_check.setVisibility(View.VISIBLE);
        } else {
            holder.img_check.setVisibility(View.INVISIBLE);
        }

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), new StringBuilder("fonts/")
                .append(fontList.get(position)).toString());

        holder.tv_font_name.setText(fontList.get(position));
        holder.tv_font_demo.setTypeface(typeface);

    }

    @Override
    public int getItemCount() {
        return fontList.size();
    }

    public class FontViewHolder extends RecyclerView.ViewHolder {
        TextView tv_font_name, tv_font_demo;
        ImageView img_check;

        public FontViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_font_demo = itemView.findViewById(R.id.tv_font_demo);
            tv_font_name = itemView.findViewById(R.id.tv_font_name);
            img_check = itemView.findViewById(R.id.img_check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFontSelected(fontList.get(getAdapterPosition()));
                    row_selected = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface FontAdapterClickListener {
        void onFontSelected(String fontName);
    }
}
