package com.example.mohit.codemania;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class Signup extends AppCompatActivity {
    Button b2;
    EditText e1,e2,e3,e4,e5,e6;
    int id;
    String arr[]={"66164","12694","54242","31561","16151"};
    //String s1="",s2="",s3="",s4="",s5="",s6="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        b2 = (Button) findViewById(R.id.signup2);
        e1 = (EditText) findViewById(R.id.txt1);
        e2 = (EditText) findViewById(R.id.txt2);
        e3 = (EditText) findViewById(R.id.txt3);
        e4 = (EditText) findViewById(R.id.txt4);
        e5 = (EditText) findViewById(R.id.txt5);
        e6 = (EditText) findViewById(R.id.txt6);
        Random rand = new Random();
        id = rand.nextInt(5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = e6.getText().toString();
                int n = s4.length(), f = 0;
                for (int i = 0; i < n; i++) {
                    if (s4.charAt(i) == '@') {
                        f = 1;
                        break;
                    }
                }
                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("")) {
                    Toast.makeText(Signup.this, "Please fill all entries", Toast.LENGTH_LONG).show();
                } else if (f == 0) {
                    Toast.makeText(Signup.this, "Invalid Email Address", Toast.LENGTH_LONG).show();
                } else if (!s2.equals(s3)) {
                    Toast.makeText(Signup.this, "Password Doesnt match, kindly confirm password", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase data = openOrCreateDatabase("codemania", MODE_PRIVATE, null); //nobody other can access
                    //it is stored in our phone only
                    data.execSQL("create table if not exists hint (name varchar, password varchar,confirm_password varchar,email varchar,codeforces varchar,phone varchar);");
                    String s = "select * from hint where name='" + s1 + "' and password='" + s2 + "'";
                    Cursor cursor = data.rawQuery(s, null); // whatever query i run i can store something in cursor it is a class
                    if (cursor.getCount() > 0) {
                        Toast.makeText(Signup.this, "User Already Exist", Toast.LENGTH_LONG).show();
                    } else {
                        data.execSQL("insert into hint values ('" + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "');");
                        Toast.makeText(Signup.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Signup.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }
        public void onBackPressed()
        {
            super.onBackPressed();
            startActivity(new Intent(Signup.this, Login.class));
            finish();
        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
