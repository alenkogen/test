package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    int inputRed;
    int inputGreen;
    int inputBlue;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText redText = findViewById(R.id.TxtRed);
        EditText greenText = findViewById(R.id.TxtGreen);
        EditText blueText = findViewById(R.id.TxtBlue);
        Button sendButton = findViewById(R.id.BtnSend);

        

        DatabaseReference colorRef = FirebaseDatabase.getInstance().getReference("Color");
        layout  = findViewById(R.id.layout);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int red, green, blue;
                red = Integer.parseInt(redText.getText().toString());
                green = Integer.parseInt(greenText.getText().toString());
                blue = Integer.parseInt(blueText.getText().toString());
                colorRef.child("Red").setValue(red);
                colorRef.child("green").setValue(green);
                colorRef.child("blue").setValue(blue);


            }
        });
        colorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inputRed = snapshot.child("Red").getValue(Integer.class);
                inputGreen = snapshot.child("green").getValue(Integer.class);
                inputBlue = snapshot.child("blue").getValue(Integer.class);
                layout.setBackgroundColor(Color.rgb(inputRed, inputGreen, inputBlue));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}