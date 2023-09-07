package com.raks.retrofit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raks.retrofit.pokemon.Species;

public class SpeciesViewHolder extends RecyclerView.ViewHolder {
    private final TextView _speciesNameTextView;
    private       Species  _species;

    public SpeciesViewHolder(@NonNull View itemView, SpeciesAdapter.OnSpeciesClickListener _onSpeciesClickListener) {
        super(itemView);
        _speciesNameTextView = itemView.findViewById(R.id.species_name);
        _speciesNameTextView.setOnClickListener(view -> {
            if (_species != null)
                _onSpeciesClickListener.onSpeciesClick(_species);
        });
    }

    public void bind(Species species) {
        _species = species;
        _speciesNameTextView.setText(species.name);
    }
}