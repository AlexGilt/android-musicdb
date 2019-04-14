package ru.alexgiltd.musicdb.util.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<E> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(E element);
}
