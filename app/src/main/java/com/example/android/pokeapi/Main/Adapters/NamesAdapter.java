package com.example.android.pokeapi.Main.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.pokeapi.CreatureDetails.CreatureDetailsActivityImpl;
import com.example.android.pokeapi.Main.Data.Models.Names.Result;
import com.example.android.pokeapi.R;

import java.util.List;

public class NamesAdapter extends ArrayAdapter<Result> {
    private Context mcon;
    public NamesAdapter(@NonNull Context context, List<Result> results) {
        super(context, 0,results);
        mcon=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Result Item = getItem(position);
        View ListItemView = convertView;
        if(ListItemView == null)
        {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.creature_name_list_item,parent,false);
        }


        TextView name = (TextView)ListItemView.findViewById(R.id.list_item_name);
        name.setText(Item.getmName());

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcon, CreatureDetailsActivityImpl.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("url" , Item.getmUrl());
                mcon.startActivity(i);
            }
        });

        return ListItemView;



    }
}
