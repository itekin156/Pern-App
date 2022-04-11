package com.example.pren;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartActivity extends AppCompatActivity
{
    private ImageButton btnNext;
    private TextInputEditText txtName;
    private RadioButton btnRad1;
    private RadioButton btnRad2;
    private String name;
    private CircleImageView imgProfile;
    private FloatingActionButton btnAdd;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        txtName = findViewById(R.id.txtName);
        btnNext = findViewById(R.id.btnNext);
        btnRad1 = findViewById(R.id.btnrad1);
        btnRad2 = findViewById(R.id.btnrad2);
        btnAdd = findViewById(R.id.btnAdd);
        imgProfile = findViewById(R.id.imgProfile);


        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {
                        //permission not granted
                        String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions , PERMISSION_CODE );

                    }
                    else {

                        //permission already granted
                        pickImageFromGallery();
                    }
                }else
                    {
                        //system os is less then marshmallow
                        pickImageFromGallery();
                    }
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name = txtName.getText().toString();
                if (name.equals(""))
                {
                    Toast.makeText(StartActivity.this, "please enter your Name", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent( StartActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString( "username", name);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case PERMISSION_CODE:{
                if(grantResults.length>= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    pickImageFromGallery();

                }else
                {
                    Toast.makeText(this, "permission denied....!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_PICK_CODE)
        {
            imgProfile.setImageURI(data.getData());
        }

    }

    private void pickImageFromGallery()
    {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }
}