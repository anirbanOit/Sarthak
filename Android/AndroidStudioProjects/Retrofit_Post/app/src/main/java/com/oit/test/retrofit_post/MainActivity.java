package com.oit.test.retrofit_post;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    private TextView mResponseTv;
    private APIService mAPIService;
    byte[] imageBytes;
    String imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText fname = findViewById(R.id.firstName);
        final EditText lname = findViewById(R.id.lastName);
        final EditText gender = findViewById(R.id.gender);
        final EditText dob = findViewById(R.id.dob);
        //final ImageView img= findViewById(R.id.photo);
        final EditText dept = findViewById(R.id.dept);
        Button submitBtn = findViewById(R.id.btn_submit);

        mResponseTv = findViewById(R.id.tv_response);

        mAPIService = ApiUtils.getAPIService();

        submitBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String firstName = fname.getText().toString().trim();
                String lastName = lname.getText().toString().trim();
                String gen= gender.getText().toString().trim();
                String db=dob.getText().toString().trim();
                String dep=dept.getText().toString().trim();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                imageBytes = baos.toByteArray();
                imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(gen) && !TextUtils.isEmpty(db) && !TextUtils.isEmpty(dep) && !TextUtils.isEmpty(imageString)) {
                    sendPost(firstName, lastName, gen, db, imageString, dep);
                }
            }
        });
    }



    public void sendPost(String firstName, String lastName,String gender,String date,String photo,String dept) {

        Post model = new Post();
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setGender(gender);
        model.setDob(date);
        model.setPhoto(photo);
        model.setDept(dept);


        mAPIService.savePost(model).enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.d(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

}