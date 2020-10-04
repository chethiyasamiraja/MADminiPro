package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HospitalRegister extends AppCompatActivity {


    EditText name, city, user, pass, con;
    Button register;
    DatabaseReference ref;
    HReg hreg;

    private void clearControls() {
        name.setText("");
        city.setText("");
        con.setText("");
        user.setText("");
        pass.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);


        name = findViewById(R.id.name);
        city = findViewById(R.id.city);

        con = findViewById(R.id.num);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);

        register = findViewById(R.id.btnreg);

        hreg = new HReg();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference().child("HReg");

                try {

                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Hospital Name is required", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(user.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Username is required", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(pass.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_LONG).show();

                    else {

                        hreg.setFname(name.getText().toString().trim());
                        hreg.setLname(city.getText().toString().trim());
                        hreg.setUser(user.getText().toString().trim());
                        hreg.setPass(pass.getText().toString().trim());
                        hreg.setCon(Integer.parseInt(con.getText().toString().trim()));


                        //ref.push().setValue(reg);
                        ref.child("HReg").setValue(hreg);
                        Toast.makeText(getApplicationContext(), "Data inserted succesfully", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(), Profile.class);
                        clearControls();

                        startActivity(i);
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}