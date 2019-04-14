package ru.alexgiltd.musicdb.util.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public abstract class BaseRecyclerAdapter<E, VH extends BaseViewHolder<E>> extends ListAdapter<E, VH> {

    private OnItemClickListener<E> onItemClickListener;

    protected BaseRecyclerAdapter(@NonNull DiffUtil.ItemCallback<E> diffCallback) {
        super(diffCallback);
    }

    public interface OnItemClickListener<E> {
        void onItemClick(E e);
    }

    @Nullable
    protected OnItemClickListener<E> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<E> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(getItem(position));
    }
}