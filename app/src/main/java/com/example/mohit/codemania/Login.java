package com.example.mohit.codemania;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1=(Button)findViewById(R.id.signup);
        b2=(Button)findViewById(R.id.login);
        e1=(EditText)findViewById(R.id.txt1);
        e2=(EditText)findViewById(R.id.txt2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();

                //database in android
                SQLiteDatabase data=openOrCreateDatabase("codemania",MODE_PRIVATE,null); //nobody other can access
                //it is stored in our phone only
                data.execSQL("create table if not exists hint (name varchar, password varchar,confirm_password varchar,email varchar,codeforces varchar,phone varchar);");
                //   data.execSQL("create table if not exists sushant (name varchar, password varchar);");
                String s = "select * from hint where name='" + s1 + "' and password='" + s2 + "'";
                Cursor cursor = data.rawQuery(s, null); // whatever query i run i can store something in cursor it is a class
                if (cursor.getCount() > 0) {
                    Toast.makeText(Login.this, "User Already Exist", Toast.LENGTH_LONG).show();
                }
                else {
                    //data.execSQL("insert into sushant values ('" + s1 + "','" + s2 + "');");
                    //Toast.makeText(MainActivity.this, "database updated", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Login.this, Signup.class);
                    startActivity(i);
                    finish();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase data=openOrCreateDatabase("codemania",MODE_PRIVATE,null); //nobody other can access
                //it is stored in our phone only
                data.execSQL("create table if not exists hint (name varchar, password varchar,confirm_password varchar,email varchar,codeforces varchar,phone varchar);");
                //
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s = "select * from hint where name='" + s1 + "' and password='" + s2 + "'";
                Cursor cursor = data.rawQuery(s, null);
                if (cursor.getCount() > 0) {
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("username", s1);
                    Intent i=new Intent(Login.this,home.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(Login.this, "Not a registered User , Kindly signup first", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}