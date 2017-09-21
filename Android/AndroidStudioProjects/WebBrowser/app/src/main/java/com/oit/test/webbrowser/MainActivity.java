package com.oit.test.webbrowser;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Go = (Button) findViewById(R.id.go);
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edit = (EditText)findViewById(R.id.text);
                // TODO Auto-generated method stub
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(edit.getText().toString()));
                startActivity(browserIntent);


                /*String url = edit.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/

            }
        });
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}


