package com.example.pren.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pren.R;

public class viewHolderHome extends RecyclerView.ViewHolder
{
    public ImageView imgHome;
    public TextView txtHome;

    public viewHolderHome(@NonNull View v)
    {
        super(v);
        imgHome =(ImageView)v.findViewById(R.id.imgHome);
        txtHome =(TextView)v.findViewById(R.id.txtHome);
    }

}
