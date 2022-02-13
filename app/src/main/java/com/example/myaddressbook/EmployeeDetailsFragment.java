package com.example.myaddressbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaddressbook.model.Employee;
import com.example.myaddressbook.model.EmployeeModel;
import com.example.myaddressbook.util.DatabaseHandler;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EmployeeDetailsFragment extends Fragment {
    public Employee employee;
    private MapView map;
    public EmployeeDetailsFragment(Employee employee) {
        this.employee=employee;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_employee_details, container, false);
        TextView tvName=view.findViewById(R.id.tv_name);
        TextView tvCity=view.findViewById(R.id.tv_city);
        TextView tvPhone=view.findViewById(R.id.tv_phone);
        TextView tvSince=view.findViewById(R.id.tv_since);
        TextView tvEmail=view.findViewById(R.id.tv_email);
        tvName.setText(employee.name.title+" "+employee.name.first+" "+employee.name.last);
        tvCity.setText("City: "+employee.location.city+","+employee.location.state);
        tvPhone.setText("Phone: "+employee.phone);
        tvSince.setText("Member since: "+getStringToDate(employee.registered.date));
        tvEmail.setText("Email: "+employee.email);
        Button btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            DatabaseHandler db= DatabaseHandler.getInstance(this.getContext());
            EmployeeModel employeeModel=
                    new EmployeeModel(
                            employee.employeeId.toString(),
                            employee.name.title+" "+employee.name.first+" "+employee.name.last,
                            employee.picture.medium,
                            employee.location.city+","+employee.location.state,
                            getStringToDate(employee.registered.date),
                            employee.email,
                            employee.phone
                    );
            if(db.insertBook(employeeModel)){
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(googleMap -> {
//            LatLng sydney = new LatLng(Double.parseDouble(employee.location.coordinates.latitude),Double.parseDouble(employee.location.coordinates.longitude));
            LatLng sydney = new LatLng(-33.852, 151.211);
            googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                    .title("Marker in Sydney"));
//                .title(employee.location.city));
        });
        return view;

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