package com.example.myaddressbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaddressbook.EmployeeDetailsFragment;
import com.example.myaddressbook.R;
import com.example.myaddressbook.model.Employee;
import com.example.myaddressbook.model.EmployeeModel;
import com.example.myaddressbook.model.HeaderEmployee;
import com.example.myaddressbook.util.DatabaseHandler;
import com.example.myaddressbook.util.ImageBitMap;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import static android.Manifest.permission.CALL_PHONE;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Vector<EmployeeModel> employeeModels;
    private Context c;
    public BookAdapter(Context context) {
        DatabaseHandler db=DatabaseHandler.getInstance(context);
        employeeModels=db.getAllBook();
        this.c=context;
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.book_item,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        EmployeeModel employee=employeeModels.get(position);
        new ImageBitMap(holder.picture).execute(employee.picture);
        holder.name.setText(employee.name);
        holder.city.setText("City: "+employee.city);
        holder.phone.setOnClickListener(v ->{
            Intent intent=new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+employee.phone));
            c.startActivity(intent);
        });
        holder.email.setOnClickListener(v->{
            Intent intent=new Intent(Intent.ACTION_SENDTO);
            intent.setType("application/octet-stream");
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{
                employee.email
            });
            intent.putExtra(Intent.EXTRA_SUBJECT,"hello");
            intent.putExtra(Intent.EXTRA_TEXT,"hello");
//            intent.setType("message/rfc822");
            c.startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });
    }
    @Override
    public int getItemCount() {
        return employeeModels!=null?employeeModels.size():0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView name,city;
        private ImageView picture;
        private CardView cvEmployee;
        private Button phone,email;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
            city=itemView.findViewById(R.id.tv_city);
            phone=itemView.findViewById(R.id.btn_call);
            email=itemView.findViewById(R.id.btn_email);
            picture=itemView.findViewById(R.id.iv_picture);
            cvEmployee=itemView.findViewById(R.id.cv_employee);
        }
    }
}

