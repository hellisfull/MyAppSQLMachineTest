package com.example.hp.myappsql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.name;

public class Display extends AppCompatActivity {
    TextView tv1, tv2, tv3;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        bt1=(Button)findViewById(R.id.buttt);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contact = db.getAllContact();
        for (Contact cn : contact) {
            String log = "Name:" + cn.getName();
            String log2 = "Place:" + cn.getPhoneno();
            String log3 = "Age :" + cn.getAge();
            Log.d("name", log);
            Log.d("age", log3);
            tv1.setText(log);
            tv2.setText(log2);
            tv3.setText(log3);
            Toast.makeText(getApplicationContext(), "completed", Toast.LENGTH_SHORT).show();
        }
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
