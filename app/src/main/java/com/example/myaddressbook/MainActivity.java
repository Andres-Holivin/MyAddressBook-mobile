package com.example.myaddressbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myaddressbook.model.Employee;
import com.example.myaddressbook.model.HeaderEmployee;
import com.example.myaddressbook.util.APIService;
import com.example.myaddressbook.util.DatabaseHandler;
import com.example.myaddressbook.util.IAPIService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.list_item);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        currentFragment=new EmployeeFragment();
        loadFragment(currentFragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.search_item:
                    currentFragment=new EmployeeFragment();
                    loadFragment(currentFragment);
                    break;
                case R.id.book_item:
                    Log.d("TAG",DatabaseHandler.getInstance(getApplicationContext()).getAllBook().toString());
                    currentFragment=new BookFragment();
                    loadFragment(currentFragment);
                    break;
            }
            return true;
        }
    };
    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_home_fl,fragment);
        fragmentTransaction.commit();
    }
}