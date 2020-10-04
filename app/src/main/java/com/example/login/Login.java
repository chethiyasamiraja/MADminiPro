package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText txt1, txt2;
    Button btn , btn2;
    TextView sign , hos;
    DatabaseReference ref;
    Add add;

    AwesomeValidation awesomeValidation;


    private void clearcontrols() {

        txt1.setText("");
        txt2.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.txt2,
                "[0-9]{6}$",R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.txt1,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        sign = findViewById(R.id.signup);
        hos = findViewById(R.id.hospital);
        btn = findViewById(R.id.btnlog);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

        hos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HospitalLogin.class);
                startActivity(i);
            }
        });


        add = new Add();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference().child("add");

               /* try {

                    if (TextUtils.isEmpty(txt1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "First Name is required", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(txt2.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_LONG).show();

                    else {

                        add.setName(txt1.getText().toString().trim());
                        add.setPass(txt2.getText().toString().trim());

                        ref.push().setValue(add);
                        Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_LONG).show();
                        clearcontrols();

                        Intent i = new Intent(getApplicationContext(), Profile.class);
                        startActivity(i);

                    }
                } finally {

                };*/



                if(awesomeValidation.validate()){
                    add.setName(txt1.getText().toString().trim());
                    add.setPass(txt2.getText().toString().trim());

                    // ref.push().setValue(add);
                    ref.child("Login").setValue(add);

                    Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_LONG).show();
                    clearcontrols();

                    Intent i = new Intent(getApplicationContext(), Profile.class);
                    startActivity(i);

                }else {
                    Toast.makeText(getApplicationContext(),"Validation Failed..",Toast.LENGTH_SHORT).show();
                }


            }
        });



    }
        }
