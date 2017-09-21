package udacity.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREF = "MyPrefs" ;
    public static final String isLoggedIn = "isLoggedIn";
    public static final String

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedpreferences = getSharedPreferences(MyPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(isLoggedIn, true);

        if(isLoggedIn){


    }

    public void login {
            EditText name = (EditText) findViewById(R.id.name);
            EditText password = (EditText) findViewById(R.id.password);

            if (name.getText().toString() == AppConstant.name && password.getText().toString() == AppConstant.password) {

                    editor.putString(name.getText().toString())
            }
        }
}
