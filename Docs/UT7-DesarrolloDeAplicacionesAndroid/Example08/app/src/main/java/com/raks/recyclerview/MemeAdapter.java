package com.raks.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemeAdapter extends RecyclerView.Adapter<MemeViewHolder> {

    public interface OnMemeClickListener {
        void onMemeClick(int position);
    }

    private final List<Meme>          _memes;
    private final OnMemeClickListener _onMemeClickListener;

    public MemeAdapter(List<Meme> memes, OnMemeClickListener onMemeClickListener) {
        _memes               = memes;
        _onMemeClickListener = onMemeClickListener;
    }

    @NonNull
    @Override
    public MemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_meme_item, parent, false);
        return new MemeViewHolder(view, _onMemeClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MemeViewHolder holder, int position) {
        Meme meme = _memes.get(position);
        holder.bind(meme);
    }

    @Override
    public int getItemCount() {
        return _memes.size();
    }

}