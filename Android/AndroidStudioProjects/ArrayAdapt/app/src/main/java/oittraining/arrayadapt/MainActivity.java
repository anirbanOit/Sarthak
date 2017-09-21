package oittraining.arrayadapt;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.resource;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    ListView listview;
    private String TAG = MainActivity.class.getSimpleName();
    private static String url = "http://profile.getsandbox.com/users";
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.lt_images);

    }

    private void makeJsonArray() {
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, response.toString());
//                        pDialog.hide();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                Contact_get_set cnt = new Contact_get_set();
                                cnt.setFirstname(obj.getString("firstName"));
                                cnt.setLastname(obj.getString("lastName"));
                                cnt.setGender((obj.getString("gender")));
                                cnt.setDob(obj.getString("dob"));
                                cnt.setDept(obj.getString("dept"));
                                cnt.setPhoto(obj.getString("photo"));
                                list.add(cnt);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                //pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

}
