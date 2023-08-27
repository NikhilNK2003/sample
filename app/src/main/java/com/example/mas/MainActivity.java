package com.example.mas;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     Button login;
     EditText etid;
     EditText etpassword;
    RadioGroup rgroup;
    RadioButton radioadmin;
    RadioButton radioemployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgroup=findViewById(R.id.radiogrp);
        login=findViewById(R.id.loginButton);
        etid=findViewById(R.id.ied);
        etpassword=findViewById(R.id.pass);

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (view.getId()==R.id.radmin)
        {
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, Adminscreen.class));
                    }
                });

            Toast.makeText(this, "Logging in as admin", Toast.LENGTH_LONG).show();
        }else if(view.getId()==R.id.remployee)
        {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, EmployeeScreen.class));
                }
            });
            Toast.makeText(this, " Logging in as employee", Toast.LENGTH_LONG).show();
        }

        }
    }

