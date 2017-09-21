package oittraining.volleyjsonparsing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    ImageView image;
    ArrayList<Contact_get_set> list;
    private String TAG = MainActivity.class.getSimpleName();
    private static String url = "http://profile.getsandbox.com/users";
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        listview = (ListView) findViewById(R.id.lt_images);
        makeJsonArray();
        adapter = new MyAdapter();
        listview.setAdapter(adapter);
    }

    private void makeJsonArray() {
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, response.toString());
//                        pDialog.hide();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Contact_get_set cnt = new Contact_get_set();
                                cnt.setFirstname(obj.getString("firstName"));
                                cnt.setLastname(obj.getString("lastName"));
                                cnt.setGender((obj.getString("gender")));
                                cnt.setDob(obj.getString("dob"));
                                cnt.setDept(obj.getString("dept"));
                                cnt.setPhoto(obj.getString("photo"));
                                list.add(cnt);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater l = getLayoutInflater();
            View v = l.inflate(R.layout.simple_row, viewGroup, false);
            image = v.findViewById(R.id.iv_image);
            TextView name = v.findViewById(R.id.tv_name);
            TextView dept = v.findViewById(R.id.tv_dept);
            Contact_get_set get_contact = list.get(i);
            name.setText(get_contact.getFirstname());
            dept.setText(get_contact.getDept());
            base64_to_image(get_contact.getPhoto());
            return v;
        }

        private void base64_to_image(String imageString) {

            byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            image.setImageBitmap(decodedImage);
            image.setVisibility(View.VISIBLE);

        }
    }
}
