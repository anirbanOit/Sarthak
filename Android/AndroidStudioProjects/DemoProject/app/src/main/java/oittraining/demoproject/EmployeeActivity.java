package oittraining.demoproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.list;

/**
 * Created by OPTLPTP098 on 9/8/2017.
 */

public class EmployeeActivity extends AppCompatActivity {
    List<Employee> employee = new ArrayList<>();
    List<Employee> filteredList = new ArrayList<>();
    RecyclerView recyclerView;
    EmployeeAdapter adapter;
    EditText search;
    ProgressDialog p;
    private static final String TAG = ContactViewFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_activity);
        recyclerView = (RecyclerView) findViewById(R.id.employee_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search = (EditText) findViewById(R.id.et_search);
        getList();
        //border();
        addTextListener();

    }

    //Parse JSON and set recyclerView adapter
    public void getList() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Employee>> call = apiService.getEmployee();
       p = new ProgressDialog(this);
        p.setMessage("Loading...");
        p.show();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                int statusCode = response.code();
                employee.addAll(response.body());
                filteredList.addAll(response.body());
                adapter = new EmployeeAdapter(filteredList, getApplicationContext());
                recyclerView.setAdapter(adapter);
                p.dismiss();
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private ShapeDrawable setBorder() {
        ShapeDrawable shapedrawable = new ShapeDrawable();
        shapedrawable.setShape(new RectShape());
        shapedrawable.getPaint().setColor(Color.BLACK);
        shapedrawable.getPaint().setStrokeWidth(10f);
        shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
        return shapedrawable;
    }

    private void border() {
        search.setBackgroundDrawable(setBorder());
    }

    //Search functionality in RecyclerView
    public void addTextListener() {

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {
                final String s = query.toString().toLowerCase();
                filteredList.clear();
                if (s.isEmpty()) {
                    filteredList.addAll(employee);
                } else {
                    for (Employee e : employee) {
                        if (e.getFirstName().toString().toLowerCase().contains(s)||e.getLastName().toString().toLowerCase().contains(s)) {
                            filteredList.add(e);
                        }
                    }
                    if (filteredList.isEmpty()) {
                        Toast.makeText(EmployeeActivity.this, "No Data Found", Toast.LENGTH_LONG).show();
                    }
                }


               /* for (int i = 0; i < employee.size(); i++) {

                    final String text = employee..toString().toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(employee.get(i));
                    }
                }*/
                recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeActivity.this));
                adapter = new EmployeeAdapter(filteredList, getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();  // data set changed
            }
        });
    }


}
