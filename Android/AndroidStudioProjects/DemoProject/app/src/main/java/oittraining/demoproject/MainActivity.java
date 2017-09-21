package oittraining.demoproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.os.Build;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import static android.text.TextUtils.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int REQUEST_WRITE_PERMISSION = 1  ;
    Spinner spinner;
    Button preview, btnAdd;
    ArrayAdapter<CharSequence> adapter;
    EditText firstname, lastname, dob;
    TextView male, female;
    ImageView contact_image, datePicker;
    LinearLayout gender, layout_dob;
    private Bitmap bitmap;
    final String regexp = "^[A-Z+a-z]+$";
    //final String regexpDate = "^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    String fname, lname, genderValue, dateofBirth, department, imageString, image_position;
    Pattern pattern_firstname, pattern_lastname, pattern_date;
    Matcher matcher_firstname, matcher_lastname, matcher_date;
    RelativeLayout mainLayout;
    private static int RESULT_LOAD = 1;
    private int age;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setOnclickListner();
        setSpinnerAdapter();
        border();
        spinner.setOnItemSelectedListener(this);
    }

    public void loadImagefromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                contact_image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }



    //Border making function
    private ShapeDrawable setBorder() {
        ShapeDrawable shapedrawable = new ShapeDrawable();
        shapedrawable.setShape(new RectShape());
        shapedrawable.getPaint().setColor(Color.BLACK);
        shapedrawable.getPaint().setStrokeWidth(10f);
        shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
        return shapedrawable;
    }

    //View variables initialization
    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner_dept);
        preview = (Button) findViewById(R.id.btnpreview);
        btnAdd = (Button) findViewById(R.id.btn_add);
        firstname = (EditText) findViewById(R.id.et_firstName);
        lastname = (EditText) findViewById(R.id.et_lastName);
        dob = (EditText) findViewById(R.id.et_dob);
        male = (TextView) findViewById(R.id.tv_male);
        female = (TextView) findViewById(R.id.tv_female);
        contact_image = (ImageView) findViewById(R.id.contact_image);
        gender = (LinearLayout) findViewById(R.id.ll_gender);
        layout_dob = (LinearLayout) findViewById(R.id.ll_dob);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        datePicker = (ImageView) findViewById(R.id.im_dob);

    }

    //setting the spinner adapter
    private void setSpinnerAdapter() {
        adapter = ArrayAdapter.createFromResource(this, R.array.departments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    //setting borders
    private void border() {
        spinner.setBackgroundDrawable(setBorder());
        preview.setBackgroundDrawable(setBorder());
        btnAdd.setBackgroundDrawable(setBorder());
        firstname.setBackgroundDrawable(setBorder());
    }

    //name validation function
    private boolean validateName() {
        pattern_firstname = Pattern.compile(regexp);
        pattern_lastname = Pattern.compile(regexp);
        matcher_firstname = pattern_firstname.matcher(fname);
        matcher_lastname = pattern_lastname.matcher(lname);

        if (matcher_lastname.matches() && matcher_firstname.matches()) {
            return true;
        } else {
            return false;
        }

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    //setting OnClickListeners
    private void setOnclickListner() {
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genderValue = male.getText().toString();
                genderToggle(genderValue);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genderValue = female.getText().toString();
                genderToggle(genderValue);

            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog();
            }
        });

        contact_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImagefromGallery();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Preview", "clicked");
                Intent i = new Intent(getApplicationContext(), EmployeeActivity.class);
                startActivity(i);
                /*((RelativeLayout)findViewById(R.id.rl_data_post)).setVisibility(View.GONE);
                ((RelativeLayout)findViewById(R.id.rl_data_get)).setVisibility(View.VISIBLE);
                ContactViewFragment contact_fragment = new ContactViewFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.rl_data_get, contact_fragment, "frag");
                transaction.commit();*/
            }
        });


    }

    //datePicker function
    private void datePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                dob.setText(s);
                dateofBirth = dob.getText().toString();
            }
        }, y, m, d);

        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        dialog.show();
    }

    //Date validation
    private boolean validateDate() {
        //pattern_date = Pattern.compile(regexpDate);
        matcher_date = pattern_date.matcher(dateofBirth);

        if (matcher_date.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //gender toggle function
    public void genderToggle(String genderSelection) {
        if (genderSelection.equalsIgnoreCase("Male")) {
            male.setBackgroundResource(R.drawable.gender_on_select);
            ShapeDrawable shapedrawable = new ShapeDrawable();
            shapedrawable.setShape(new RectShape());
            shapedrawable.getPaint().setColor(Color.BLACK);
            shapedrawable.getPaint().setStrokeWidth(10f);
            shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
            female.setBackgroundDrawable(shapedrawable);
        }
        if (genderSelection.equalsIgnoreCase("Female")) {
            female.setBackgroundResource(R.drawable.gender_on_select);
            ShapeDrawable shapedrawable = new ShapeDrawable();
            shapedrawable.setShape(new RectShape());
            shapedrawable.getPaint().setColor(Color.BLACK);
            shapedrawable.getPaint().setStrokeWidth(10f);
            shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
            male.setBackgroundDrawable(shapedrawable);
        }
    }

    //get data
    private void getData() {
        fname = firstname.getText().toString();
        lname = lastname.getText().toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getItemAtPosition(i);
        TextView mytext = (TextView) view;
        department = mytext.getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void add() {
        getData();
        convertToBase64();
        sendData(fname, lname, genderValue, dateofBirth, department, imageString);
       /* if (!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(lname) && TextUtils.isEmpty(genderValue) && TextUtils.isEmpty(dateofBirth) && TextUtils.isEmpty(department) && TextUtils.isEmpty(imageString)) {
                sendData(fname, lname, genderValue, age, department, imageString);
            }else{
            Toast.makeText(MainActivity.this, "Field Empty", Toast.LENGTH_SHORT).show();
        }*/
        }

    //Send Data to the server
    public void sendData(String firstName, String lastname, String genderValue, String dateofBirth, String department, String imageString) {
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastname);
            employee.setDept(department);
            employee.setPhoto(imageString);
            employee.setGender(genderValue);
            employee.setDob(dateofBirth);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Employee> call = apiService.savePost(employee);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

               if(response.isSuccessful()){
                   Log.i(TAG, "post submitted to API." + response.body().toString());
                   Toast.makeText(MainActivity.this, "post submitted to API.", Toast.LENGTH_LONG).show();
                   AlertDialog.Builder builder;
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       builder = new AlertDialog.Builder(MainActivity.this);
                   } else {
                       builder = new AlertDialog.Builder(MainActivity.this);
                   }
                   builder.setTitle("Add User")
                           .setMessage("Data Inserted Succesfully.")
                           .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int which) {
                                   // continue with delete
                               }
                           })
                           .setIcon(android.R.drawable.ic_dialog_alert)
                           .show();
               }
            }


            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.e(TAG, t.toString());
                Toast.makeText(MainActivity.this, "Data Insertion Faliure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Convert Contact Image to base 64
    public void convertToBase64() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap = ((BitmapDrawable) contact_image.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

}

