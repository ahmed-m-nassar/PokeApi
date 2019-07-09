package com.example.android.pokeapi.Adapters.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.pokeapi.CreatureDetails;
import com.example.android.pokeapi.Models.Names.Result;
import com.example.android.pokeapi.R;

import java.util.List;

public class AbilitiesAdapter extends ArrayAdapter<String> {
    private Context mcon;
    public AbilitiesAdapter(@NonNull Context context, List<String> names) {
        super(context, 0,names);
        mcon=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final String Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.creature_abilities_list_item,parent,false);
        }

        TextView name = (TextView)ListItemView.findViewById(R.id.Creature_Abilities_Item_Name);
        name.setText(Item);

        return ListItemView;



    }
}
