package com.example.hp.myappsql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button bt;
    EditText ev,ev2,ev3;
    /*TextView tv1,tv2,tv3;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button)findViewById(R.id.butt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(getApplicationContext(),Display.class);
                startActivity(ii);

                ev=(EditText)findViewById(R.id.ed);
                ev2=(EditText)findViewById(R.id.ed2);
                ev3=(EditText)findViewById(R.id.ed3);
                /*tv1=(TextView)findViewById(R.id.tv1);
                tv2=(TextView)findViewById(R.id.tv2);
                tv3=(TextView)findViewById(R.id.tv3);*/
                String name= ev.getText().toString();
                String place=ev2.getText().toString();
                String age=ev3.getText().toString();
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
               db.addContact(new Contact(name,place,age));
                List<Contact> contact = db.getAllContact();
                for (Contact cn: contact  ) {
                    String log = "Name:" + cn.getName() ;
                    String log2="Place:" + cn.getPhoneno();
                   String log3="Age :" +cn.getAge();
                    Log.d("name",log);
                    Log.d("place",log2);
                    Log.d("age",log3);
                   /* tv1.setText(log);
                    tv2.setText(log2);
                   tv3.setText(log3);*/
                    Toast.makeText(getApplicationContext(),"completed",Toast.LENGTH_SHORT).show();
              }
                }


        });

    }
    }
