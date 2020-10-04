package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MyInfoUpdate extends AppCompatActivity {


    EditText fname, lname, user, password, contact;
    Button button1,button2;

    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_update);


        fname = findViewById(R.id.editfname);
        lname = findViewById(R.id.editlname);
        user = findViewById(R.id.edituser);
        password = findViewById(R.id.editpass);
        contact = findViewById(R.id.editcon);


        button1 = findViewById(R.id.btnupdate);
        button2 = findViewById(R.id.btndelete);

        String fn = getIntent().getStringExtra("fn");
        String ln = getIntent().getStringExtra("ln");
        String un = getIntent().getStringExtra("un");
        String pw = getIntent().getStringExtra("pw");
        String cn = getIntent().getStringExtra("cn");


        fname.setText(fn);
        lname.setText(ln);
        user.setText(un);
        password.setText(pw);
        contact.setText(cn);




        button1.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                String fn = fname.getText().toString();
                String ln = lname.getText().toString();
                String us = user.getText().toString();
                String pw = password.getText().toString();
                String cn = contact.getText().toString();


                ref = FirebaseDatabase.getInstance().getReference().child("Reg");

                HashMap hashMap = new HashMap();

                hashMap.put("fname",fn);
                hashMap.put("lname",ln);
                hashMap.put("user",us);
                hashMap.put("password",pw);
                hashMap.put("contact",cn);


                ref.child("Reg").updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getApplicationContext(),"Successfully Updated", Toast.LENGTH_LONG).show();
                        //Intent i = new Intent(getApplicationContext(), ViewPage.class);
                        //startActivity(i);
                    }
                });
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference refe = FirebaseDatabase.getInstance().getReference().child("Reg");
                refe.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("Reg")){
                            ref = FirebaseDatabase.getInstance().getReference().child("Reg").child("Reg");
                            ref.removeValue();
                            Toast.makeText(getApplicationContext(),"Data Deleted Successfully",Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(getApplicationContext(),"No Source To Delete", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });




    }
}