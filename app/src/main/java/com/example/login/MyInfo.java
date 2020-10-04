package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyInfo extends AppCompatActivity {

    TextView a, b, c, d, e;
    Button btn;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        a = findViewById(R.id.fname);
        b = findViewById(R.id.lname);
        c = findViewById(R.id.user);
        d = findViewById(R.id.pass);
        e = findViewById(R.id.contact);

        btn = findViewById(R.id.btnupdate);

        DatabaseReference Ref = FirebaseDatabase.getInstance().getReference().child("Reg").child("Reg");
        Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){
                    a.setText(dataSnapshot.child("fname").getValue().toString());
                    b.setText(dataSnapshot.child("lname").getValue().toString());
                    c.setText(dataSnapshot.child("user").getValue().toString());
                    d.setText(dataSnapshot.child("pass").getValue().toString());
                    e.setText(dataSnapshot.child("con").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        String fn = getIntent().getStringExtra("fn");
        String ln = getIntent().getStringExtra("ln");
        String un = getIntent().getStringExtra("un");
        String pw = getIntent().getStringExtra("pw");
        String cn = getIntent().getStringExtra("cn");


        a.setText(fn);
        b.setText(ln);
        c.setText(un);
        d.setText(pw);
        e.setText(cn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fnm = a.getText().toString();
                String lnm = b.getText().toString();
                String use = c.getText().toString();
                String pas = d.getText().toString();
                String con = e.getText().toString();

                Intent i = new Intent(getApplicationContext(), MyInfoUpdate.class);

                i.putExtra("fn", fnm);
                i.putExtra("ln", lnm);
                i.putExtra("un", use);
                i.putExtra("pw", pas);
                i.putExtra("cn", con);


                startActivity(i);
            }
        });


    }
}