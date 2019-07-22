package com.example.myprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText e1,et;
ContentValues val = new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editText);
        et=findViewById(R.id.editText2);

    }

    public void dosave(View view) {
        val.put("emp_name",e1.getText().toString());
        val.put("emp_profile",et.getText().toString());
        Uri uri=getContentResolver().insert(CompanyProvider.Content_uri,val);
        Toast.makeText(this,uri.toString(),Toast.LENGTH_SHORT).show();

    }

    public void doload(View view) {
        Cursor cr=getContentResolver().query(CompanyProvider.Content_uri,null,null,null,"_id");
        StringBuilder st = new StringBuilder();

     while(cr.moveToNext())
     {
    int id=cr.getInt(0);
    String s1=cr.getString(1);
    String s2=cr.getString(2);
    st.append(id +"     "+s1+"   "+s2+"   \n ");
}
           Toast.makeText(this,st.toString(),Toast.LENGTH_SHORT).show();
    }

}
