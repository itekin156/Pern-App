package com.example.pren.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pren.Model.Home;
import com.example.pren.R;
import com.example.pren.viewHolder.viewHolderHome;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<viewHolderHome>
{
    private Context context;
    private ArrayList<Home> home;
    public AdapterHome(Context context , ArrayList<Home> home)
    {
        this.context = context;
        this.home = home;
    }

    @NonNull
    @Override
    public viewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent,false);
        viewHolderHome viewHolderHome = new viewHolderHome(view);
        return viewHolderHome;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderHome viewHolderHome, int position)
    {
        viewHolderHome.txtHome.setText(home.get(position).getTxtHome());
        viewHolderHome.imgHome.setImageResource(home.get(position).getImgHome());

    }

    @Override
    public int getItemCount() {
        return home.size();
    }
}
