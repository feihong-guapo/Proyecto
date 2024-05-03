package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageViewHolder> {

    private Context mContext;
    private List<Integer> mLayouts;

    public PageAdapter(Context context, List<Integer> layouts) {
        mContext = context;
        mLayouts = layouts;
    }
    public void updateData(List<Integer> newData) {
        mLayouts.clear();
        mLayouts.addAll(newData);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de cada página
        View view = LayoutInflater.from(mContext).inflate(mLayouts.get(viewType), parent, false);
        return new PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
        // No necesitas hacer nada aquí si cada diseño de página se encarga de sus propios datos
    }

    @Override
    public int getItemCount() {
        return mLayouts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class PageViewHolder extends RecyclerView.ViewHolder {
        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

