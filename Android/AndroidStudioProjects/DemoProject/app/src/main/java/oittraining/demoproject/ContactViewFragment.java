package oittraining.demoproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by OPTLPTP098 on 9/8/2017.
 */

public class ContactViewFragment extends Fragment {

    List<Employee> employee;
    private RecyclerView recyclerView;
    EmployeeAdapter adapter;
    private static final String TAG = ContactViewFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View Layout = inflater.inflate(R.layout.employee_list_layout, container, false);
        recyclerView =  recyclerView.findViewById(R.id.employee_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getList();
        return Layout;


    }

    public void getList(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Employee>> call = apiService.getEmployee();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                int statusCode = response.code();
                employee = response.body();
                adapter = new EmployeeAdapter(employee,  getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
