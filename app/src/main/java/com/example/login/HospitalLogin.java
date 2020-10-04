package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HospitalLogin extends AppCompatActivity {


    EditText txt1, txt2;
    Button btn , btn2;
    TextView sign;
    DatabaseReference ref;
    Hospital hospital;

    private void clearcontrols() {

        txt1.setText("");
        txt2.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_login);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        sign = findViewById(R.id.signup);
        btn = findViewById(R.id.btnlog);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HospitalRegister.class);
                startActivity(i);
            }
        });


        hospital = new Hospital();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference().child("hospital");

                try {

                    if (TextUtils.isEmpty(txt1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "First Name is required", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(txt2.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_LONG).show();

                    else {

                        hospital.setName(txt1.getText().toString().trim());
                        hospital.setPass(txt2.getText().toString().trim());

                        ref.push().setValue(hospital);
                        Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_LONG).show();
                        clearcontrols();

                        Intent i = new Intent(getApplicationContext(), Profile.class);
                        startActivity(i);

                    }
                } finally {

                };
            }
        });


    }
}
