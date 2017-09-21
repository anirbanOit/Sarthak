package com.oit.test.oit2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button prev = (Button) findViewById(R.id.buttonPrev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("button","Button clicked");
                // TODO Auto-generated method stub
               // String myName = "Hello Kuntal!";
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                //i.putExtra("name",myName);
                startActivity(i);
            }
        });
        Log.d("workflow","onCreate "+count++);
        String myName = "";
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            myName = extras.getString("name");
        }
        Log.d("myname","my name : "+myName);

        TextView tv = (TextView) findViewById(R.id.myName);

        tv.setText(myName);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("workflow","onStart "+count++);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("workflow","onResume "+count++);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("workflow","onPause "+count++);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("workflow","onStop "+count++);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("workflow","onDestroy "+count++);
    }
}
