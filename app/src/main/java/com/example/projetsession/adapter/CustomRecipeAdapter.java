package com.example.projetsession.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.projetsession.R;
import com.example.projetsession.model.Recipe;

import java.util.ArrayList;

public class CustomRecipeAdapter extends ArrayAdapter<Recipe> {
    public CustomRecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        super(context, 0, recipes);
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recipe, parent, false);
        }

        Recipe recipe = getItem(position);

        TextView recipeTitle = (TextView) convertView.findViewById(R.id.recipeTitle);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.recipeImage);

        recipeImage.setImageBitmap(recipe.getImage());
        recipeTitle.setText(recipe.getTitle());

        return convertView;
    }
}
