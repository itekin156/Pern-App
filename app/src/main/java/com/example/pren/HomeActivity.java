package com.example.pren;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pren.Model.Home;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
{
    private TextView txtNameHome;
    private TextView txtNumber;

    private ArrayList<Home> home;
    private RecyclerView recyclerHome;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private String name;
    private String number;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtNameHome = findViewById(R.id.txtNameHome);
        txtNumber = findViewById(R.id.txtNumber);
        recyclerHome = findViewById(R.id.recyclerViewHome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        name = bundle.getString("username");
        number = bundle.getString("number");

        txtNameHome.setText(name);
        txtNumber.setText(number);

        home = new ArrayList<>();
        home.add(new Home(  "Letters", R.drawable.images));
        home.add(new Home(  "Grammar", R.drawable.grammar));
        home.add(new Home(  "chats", R.drawable.chat));
        home.add(new Home(  "lesson", R.drawable.lesson));
        home.add(new Home(  "Exercise", R.drawable.exercise));

        recyclerHome = findViewById(R.id.recyclerViewHome);
        recyclerHome.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerHome.setLayoutManager(layoutManager);

    }


}