package com.example.myaddressbook.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.myaddressbook.model.EmployeeModel;

import java.util.Vector;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="address_book ";
    private static final String TABLE_NAME = "employee";

    // column tables
    private static final String ID = "id";
    private static final String EMPLOYEEID = "employeeid";
    private static final String NAME = "name";
    private static final String PICTURE = "picture";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String EMAIL = "email";
    private static final String PHONE = "cell";
    private static Context c;
    private static DatabaseHandler instance;
    public static synchronized DatabaseHandler getInstance(Context context){
        c=context;
        if(instance==null){
            instance=new DatabaseHandler(context.getApplicationContext());
        }
        return instance;
    }
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE=
                "CREATE TABLE " +TABLE_NAME+" ( "
                        +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                        +EMPLOYEEID+" INTEGER ,"
                        +PICTURE+" TEXT,"
                        +NAME+" TEXT ,"
                        +CITY+" TEXT ,"
                        +STATE+" TEXT ,"
                        +EMAIL+" TEXT ,"
                        +PHONE+" TEXT );";
        Log.d("TAG", CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertBook(EmployeeModel employee){
        SQLiteDatabase db=getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values= new ContentValues();
            values.put(EMPLOYEEID,employee.employeeId);
            values.put(PICTURE,employee.picture);
            values.put(NAME,employee.name);
            values.put(CITY,employee.city);
            values.put(STATE,employee.register);
            values.put(EMAIL,employee.email);
            values.put(PHONE,employee.phone);
            db.insertOrThrow(TABLE_NAME,null,values);
            db.setTransactionSuccessful();
            db.endTransaction();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Vector<EmployeeModel> getAllBook(){
        String query="SELECT * from "+TABLE_NAME;
        return getMultipleTask(query);
    }
    private Vector<EmployeeModel> getMultipleTask(String query){
        Vector<EmployeeModel> employees=new Vector<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(query,null);
        try{
            if(cursor.moveToFirst()){
                do{
                    employees.add(parseEmployee(cursor));
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null&&!cursor.isClosed()){
                cursor.close();
            }
        }
        return employees;
    }
    private EmployeeModel parseEmployee(Cursor cursor){
        EmployeeModel employeeModel=new EmployeeModel();
        employeeModel.id=cursor.getString(0);
        employeeModel.employeeId=cursor.getString(1);
        employeeModel.picture=cursor.getString(2);
        employeeModel.name=cursor.getString(3);
        employeeModel.city=cursor.getString(4);
        employeeModel.register =cursor.getString(5);
        employeeModel.email=cursor.getString(6);
        employeeModel.phone=cursor.getString(7);
        return employeeModel;
    }
}
