package oittraining.demoproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by OPTLPTP098 on 9/8/2017.
 */

public interface ApiInterface {
    @POST("/addUser")
    Call<Employee> savePost (@Body Employee employee);

    @GET("/users")
    Call<List<Employee>> getEmployee();
}
