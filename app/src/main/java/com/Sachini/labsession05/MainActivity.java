package com.Sachini.labsession05;

import static android.widget.Toast.LENGTH_LONG;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editCourse,editMarks,updateMarks;
    Button btnAddData, btnViewAll, btnUpdateData, btnDeleteData;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper( this);

        editName = findViewById(R.id.editTextTextPersonName1);
        editCourse = findViewById(R.id.editTextTextPersonName2);
        editMarks = findViewById(R.id.editTextTextPersonName3);
        updateMarks = findViewById(R.id.editTextTextPersonName4);
        btnAddData = findViewById(R.id.buttonAddData);
        btnViewAll = findViewById(R.id.buttonViewData);
        btnUpdateData = findViewById(R.id.buttonUpdateData);
        btnDeleteData = findViewById(R.id.buttonDeleteData);

        addData();
    }
    public void addData(){
        btnAddData.setOnClickListener(
                v -> {
                    boolean isInserted = myDb.insertData(editName.getText().toString(),editCourse.getText().toString(),
                            editMarks.getText().toString());
                    if(isInserted)
                        Toast.makeText(MainActivity.this,"Data Inserted",LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Data not Inserted",LENGTH_LONG).show();
                }
        );
        viewAll();
    }
    public void viewAll() {
        btnViewAll.setOnClickListener(
                v -> {
                    Cursor results = myDb.getAllData();
                    if(results.getCount()==0) {
                        showMessage("Error Message : ", "No data found");
                        return;
                    }
                    StringBuilder buffer = new StringBuilder();
                    while(results.moveToNext()) {
                        buffer.append("City :").append(results.getString(0)).append("\n");
                        buffer.append("Name :").append(results.getString(1)).append("\n");
                        buffer.append("DOB :").append(results.getString(2)).append("\n");
                        buffer.append("NIC :").append(results.getString(3)).append(" \n\n");

                        showMessage("List of Data :", buffer.toString());
                }
                }
        );
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

