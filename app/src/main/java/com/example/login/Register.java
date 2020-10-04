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

public class Register extends AppCompatActivity {

    EditText fname, lname, user, pass, con;
    Button register;
    DatabaseReference ref;
    Reg reg;

    private void clearControls() {
        fname.setText("");
        lname.setText("");
        con.setText("");
        user.setText("");
        pass.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        con = findViewById(R.id.contact);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);

        register = findViewById(R.id.btnreg);


        reg = new Reg();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference().child("Reg");

                try {

                    if (TextUtils.isEmpty(fname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "First Name is required", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(user.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Username is required", Toast.LENGTH_LONG).show();

                    else if (TextUtils.isEmpty(pass.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_LONG).show();

                    else {

                        reg.setFname(fname.getText().toString().trim());
                        reg.setLname(lname.getText().toString().trim());
                        reg.setUser(user.getText().toString().trim());
                        reg.setPass(pass.getText().toString().trim());
                        reg.setCon(Integer.parseInt(con.getText().toString().trim()));


                        //ref.push().setValue(reg);
                        ref.child("Reg").setValue(reg);
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