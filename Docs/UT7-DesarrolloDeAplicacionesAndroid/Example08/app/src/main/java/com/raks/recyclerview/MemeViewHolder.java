package com.raks.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView                        _nameTextView;
    private final TextView                        _topTextView;
    private final ImageView                       _imageView;
    private final TextView                        _bottomTextView;
    private final MemeAdapter.OnMemeClickListener _onMemeClickListener;

    public MemeViewHolder(@NonNull View itemView, MemeAdapter.OnMemeClickListener onMemeClickListener) {
        super(itemView);
        _nameTextView        = itemView.findViewById(R.id.name);
        _topTextView         = itemView.findViewById(R.id.top_text);
        _imageView           = itemView.findViewById(R.id.image);
        _bottomTextView      = itemView.findViewById(R.id.bottom_text);
        _onMemeClickListener = onMemeClickListener;
        itemView.setOnClickListener(this);
    }

    public void bind(Meme meme) {
        _nameTextView.setText(meme.name);
        _topTextView.setText(meme.topText);
        _imageView.setImageResource(meme.imageResId);
        _bottomTextView.setText(meme.bottomText);
    }

    @Override
    public void onClick(View view) {
        _onMemeClickListener.onMemeClick(getAdapterPosition());
    }
}