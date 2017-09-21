package oittraining.fetchimage;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    ImageView image;
    ArrayList<Contact_get_set> list =  null;
    private String TAG = MainActivity.class.getSimpleName();
    private static String url = "http://profile.getsandbox.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.lt_images);
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();
            list = new ArrayList<Contact_get_set>();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            if(jsonStr!=null){
                try{
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Contact_get_set cnt = new Contact_get_set();
                        cnt.setFirstname(obj.getString("firstName"));
                        cnt.setLastname(obj.getString("lastName"));
                        cnt.setDept(obj.getString("dept"));
                        cnt.setPhoto(obj.getString("photo"));
                        list.add(cnt);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Myadapter m = new Myadapter();
            listview.setAdapter(m);
        }
    }

    private class Myadapter extends BaseAdapter {


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
            View v = l.inflate(R.layout.single_row, viewGroup, false);
            image = v.findViewById(R.id.iv_image);
            TextView name = v.findViewById(R.id.tv_name);
            TextView dept = v.findViewById(R.id.tv_dept);
            Contact_get_set cnt_get = list.get(i);
            name.setText(cnt_get.getFirstname());
            dept.setText(cnt_get.getDept());
            base64_to_image(cnt_get.getPhoto());
            return v;
        }
        private void base64_to_image(String imageString)
        {

            byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT); 
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            image.setImageBitmap(decodedImage);
            image.setVisibility(View.VISIBLE);

        }
    }
}
