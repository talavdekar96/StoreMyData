package com.example.storemydata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText mFirstname, mSurname, mMarks, mID;
    Button mAdd, mViewdata, mUpdate, mDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);
        mFirstname = (EditText) findViewById(R.id.firstname);
        mSurname = (EditText) findViewById(R.id.surname);
        mID = (EditText) findViewById(R.id.id);
        mMarks = (EditText) findViewById(R.id.marks);
        mAdd = (Button) findViewById(R.id.adddata);
        mViewdata = (Button) findViewById(R.id.viewadata);
        mUpdate = (Button) findViewById(R.id.update);
        mDelete = (Button) findViewById(R.id.delete);

        mAdd();
        viewAll();
        updatedata();
        deletedata();
    }

    public void mAdd(){
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean insertdata = mydb.insertdata(mFirstname.getText().toString(),
                        mSurname.getText().toString(),
                        mMarks.getText().toString());

               if (insertdata == true){
                   Toast.makeText(MainActivity.this,"Data inserted", Toast.LENGTH_LONG).show();
               }
               else {
                   Toast.makeText(MainActivity.this,"Data not inserted", Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    public void viewAll(){
        mViewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = mydb.getAllData();

                if (cursor.getCount() == 0){
                    showMessage("Error", "No data found");
                    return;
                }
                else {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()){
                        stringBuffer.append("ID : " + cursor.getString(0) +"\n");
                        stringBuffer.append("FIRSTNAME : " + cursor.getString(1) +"\n");
                        stringBuffer.append("SURNAME : " + cursor.getString(2) +"\n");
                        stringBuffer.append("MARKS : " + cursor.getString(3) +"\n\n");
                    }
                    showMessage("Data", stringBuffer.toString());
                }
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updatedata(){
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = mydb.updatedata(mID.getText().toString(),mFirstname.getText().toString(),
                        mSurname.getText().toString(), mMarks.getText().toString());

                if (isUpdate == true){
                    Toast.makeText(MainActivity.this,"Data is updates", Toast.LENGTH_LONG).show();
            }
               else {
                Toast.makeText(MainActivity.this,"Data not updated", Toast.LENGTH_LONG).show();
            }

        }
        });
    }

    public void deletedata(){
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleterow = mydb.deletedata(mID.getText().toString());

                if (deleterow > 0){
                    Toast.makeText(MainActivity.this,"Data is deleted", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Data not deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
