package com.example.pren;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pren.viewHolder.DatabaseHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartActivity extends AppCompatActivity
{
    private ImageButton btnNext;
    private TextInputEditText edtTextName;
    private TextInputEditText edtTextNumber;

    private DatabaseHolder myDb;
    private RadioButton btnRad1;
    private RadioButton btnRad2;
    private String name;
    private String number;

    private CircleImageView imgProfile;
    private FloatingActionButton btnAdd;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        edtTextName = findViewById(R.id.txtName);
        edtTextNumber = findViewById(R.id.edtTextNumber);
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
                name = edtTextName.getText().toString();

                number = edtTextNumber.getText().toString();
                if (name.equals(""))
                {
                    Toast.makeText(StartActivity.this, "please enter your Name", Toast.LENGTH_SHORT).show();
                }
                if (edtTextNumber.getText().toString().equals(""))
                {
                    Toast.makeText(StartActivity.this, "please enter your Number", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent( StartActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString( "username", name);
                bundle.putString( "number", number);
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

    public void AddData()
    {
        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isInserted = myDb.insertData(edtTextName.getText().toString(), edtTextNumber.getText().toString());
                if(isInserted == true)
                    Toast.makeText(StartActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(StartActivity.this,"Data could not be Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
   /* public void getData()
    {
        btngetData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = editTextId.getText().toString();
                if(id.equals(String.valueOf(""))){
                    editTextId.setError("Enter id to get data");
                    return;
                }
                Cursor res = myDb.getData(id);
                String data = null;
                if (res.moveToFirst())
                {

                    data = "Id:"+res.getString(0)+"\n"+ "Name :"+ res.getString(1)+"\n\n"+  "Surname :"+ res.getString(2)+"\n\n"+
                            "Marks :"+ res.getString(3)+"\n\n";
                }
                showMessage("Data", data);
            }
        });
    }

    public void viewAll(){
        btnviewAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor res=myDb.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id:"+res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n\n");
                    buffer.append("Surname :"+ res.getString(2)+"\n\n");
                    buffer.append("Marks :"+ res.getString(3)+"\n\n");
                }

                showMessage("Data",buffer.toString());
            }
        });
    }

    public void updateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),editMarks.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(HomeActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(HomeActivity.this,"Data could not be Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void deleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(HomeActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(HomeActivity.this,"Data could not be Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void showMessage(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}*/


}