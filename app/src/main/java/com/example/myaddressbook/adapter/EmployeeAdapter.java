package com.example.myaddressbook.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaddressbook.EmployeeDetailsFragment;
import com.example.myaddressbook.EmployeeFragment;
import com.example.myaddressbook.MainActivity;
import com.example.myaddressbook.R;
import com.example.myaddressbook.model.Employee;
import com.example.myaddressbook.model.EmployeeModel;
import com.example.myaddressbook.model.HeaderEmployee;
import com.example.myaddressbook.util.ImageBitMap;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private HeaderEmployee employeeModels;
    private Context c;
    public EmployeeAdapter(Context context) {
        this.c=context;
    }
    public void setEmployee(HeaderEmployee employee){
        this.employeeModels=employee;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.employee_item,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee=employeeModels.employees.get(position);
        new ImageBitMap(holder.picture).execute(employee.picture.medium);
        holder.name.setText(employee.name.title+" "+employee.name.first+" "+employee.name.last);
        holder.city.setText("City: "+employee.location.city+","+employee.location.state);
        holder.phone.setText("Phone: "+employee.phone);
        holder.registered.setText("Member since: "+getStringToDate(employee.registered.date));
        holder.cvEmployee.setOnClickListener(view -> {
            Fragment detailsFragment=new EmployeeDetailsFragment(employee);
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_home_fl, detailsFragment)
                    .commit();
        });
    }
    @Override
    public int getItemCount() {
        return employeeModels!=null?employeeModels.employees.size():0;
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView name,city,phone,registered;
        private ImageView picture;
        private CardView cvEmployee;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
            city=itemView.findViewById(R.id.tv_city);
            phone=itemView.findViewById(R.id.tv_phone);
            registered=itemView.findViewById(R.id.tv_since);
            picture=itemView.findViewById(R.id.iv_picture);
            cvEmployee=itemView.findViewById(R.id.cv_employee);
        }
    }
    private String getStringToDate(String date){
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            calendar.setTime(simpleDateFormat.parse(date));
            return getMonthForInt(calendar.get(Calendar.MONTH))+" "+calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }
}

