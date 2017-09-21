package com.oit.test.login;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private EditText email,password;
    private Button Signup;
    private com.google.android.gms.common.SignInButton SignIn;
    Pattern p,p1;
    Matcher m,m1;
    LinearLayout ll;
    GoogleApiClient googleApiClient;

    private static final int REQ_CODE = 9001;
    public static final String regEx = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClickListener();
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this ).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

    }
    private void initView(){
        ll = (LinearLayout)findViewById(R.id.ll_layout);
        email = (EditText) findViewById(R.id.email_et);
        password = (EditText) findViewById(R.id.password_et);
        Signup = (Button) findViewById(R.id.login_btn);
        SignIn = (com.google.android.gms.common.SignInButton) findViewById(R.id.gmailSignIn_btn);
    }

    private void onClickListener()
    {

        //Sign Up Validation
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onValidation();
            }
        });

        //Sign In
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //Hide Keyboard OnClick

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
    }

    private void onValidation()
    {
        p = Pattern.compile(regEx);
        m = p.matcher(email.getText().toString());
        p1= Pattern.compile(PASSWORD_PATTERN);
        m1= p1.matcher(password.getText().toString());
        if (!m.matches()) {
            email.setError("EMAIL NOT VALID");
        }
        if (!m1.matches()) {
            password.setError("Password not valid");
        }
    }

        private void signIn(){
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent,REQ_CODE);
        }

        private void handleResult(GoogleSignInResult result){


        }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess())
            {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
                //i.putExtra("context",getApplicationContext());
                finish();
            }else{
                Toast.makeText(MainActivity.this, "Gmail not signed up!", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "REQUESTCODE didnt matched", Toast.LENGTH_LONG).show();
        }

    }
}

