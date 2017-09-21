package com.oit.test.oit2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button next = (Button) findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("button","Button clicked");
                // TODO Auto-generated method stub
                String myName = "Hello Kuntal!";
                Intent i = new Intent(getApplicationContext(),SecondActivity.class);
                i.putExtra("name",myName);
                startActivity(i);
            }
        });
    }
}
