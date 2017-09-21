package oittraining.demoproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by OPTLPTP098 on 9/8/2017.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> {
    List<Employee> employee = Collections.emptyList();
    List<Employee> mFilteredList = Collections.emptyList();
   // private LayoutInflater inflater;
    Bitmap image;
    String base;
    int rowLayout;
    Context context;


    public EmployeeAdapter(List<Employee> employee, Context context) {
        //inflater = LayoutInflater.from(context);
        this.employee = employee;
       // this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public EmployeeAdapter.EmployeeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        EmployeeHolder holder = new EmployeeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EmployeeHolder holder, int position) {
        holder.name.setText(employee.get(position).getFirstName()+" "+employee.get(position).getLastName());
       //holder.dateofBirth.setText(employee.get(position).getDob());
        /*String d = employee.get(position).getDob();
        String[] date =  d.split("/");
        int age = getAge(date);
        holder.dateofBirth.setText(age);*/
        age_calculation(holder, employee.get(position).getDob());
        holder.department.setText(employee.get(position).getDept());
        base = employee.get(position).getPhoto();
        image = convertToImage(base);
        holder.image.setImageBitmap(image);

    }

    public int getAge(String[] date){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        //dob.set(year, month , day);

        //int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        int age = today.get(Calendar.YEAR)-year;

        if (today.get(Calendar.DAY_OF_YEAR) < day){
            age--;
        }

        /*/*//*Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();*/

        return age;
    }

    void age_calculation(EmployeeHolder holder, String dob) {

        if (dob.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})") || dob.matches("([0-9])/([0-9])/([0-9]{4})")) {
            String [] dateParts = dob.split("/");
            String day = dateParts[1];
            String month = dateParts[0];
            String year = dateParts[2];
            int monthint = Integer.parseInt(month);
            int yearint = Integer.parseInt(year);
            Calendar c = Calendar.getInstance();
            int current = c.get(Calendar.YEAR);
            int monthcur = c.get(Calendar.MONTH);
            if (monthint < monthcur) {
                holder.dateofBirth.setText("" + (current - yearint ) + "years");
            } else if(current != yearint)
                holder.dateofBirth.setText("" + ((current - yearint) - 1) + "years" );
            else
                holder.dateofBirth.setText("" + (current - yearint) + "years");
        } else
            holder.dateofBirth.setText(dob);

    }


    public Bitmap convertToImage(String imageString){
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    class EmployeeHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, dateofBirth, department;

        public EmployeeHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_image);
            name = itemView.findViewById(R.id.tv_name);
            dateofBirth = itemView.findViewById(R.id.tv_dob);
            department = itemView.findViewById(R.id.tv_dept);
        }
    }


}
