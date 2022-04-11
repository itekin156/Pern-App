package com.example.pren;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;

public class HomeActivity extends AppCompatActivity
{
    private TextView txtNameHome;
    private TextView txtNumber;

    private String name;
    private String number;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtNameHome = findViewById(R.id.txtNameHome);
        txtNumber = findViewById(R.id.txtNumber);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        name = bundle.getString("username");
        number = bundle.getString("number");

        txtNameHome.setText(name);
        txtNumber.setText(number);

    }


}