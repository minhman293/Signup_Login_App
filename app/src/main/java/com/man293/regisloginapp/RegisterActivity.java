package com.man293.regisloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPass, etConfirmPass;
    Button btnRegis;
    TextView tvAlreadyHaveAcc;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.inputUserName);
        etPass = findViewById(R.id.inputPass);
        etConfirmPass = findViewById(R.id.inputConfirmPass);
        btnRegis = findViewById(R.id.btnRegis);
        tvAlreadyHaveAcc = findViewById(R.id.titleHaveAcc);
        db = new DBHelper(this);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, pass, cfpass;
                username = etUsername.getText().toString();
                pass = etPass.getText().toString();
                cfpass = etConfirmPass.getText().toString();

                // check if there is infill field
                if (username.equals("") || pass.equals("") || cfpass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                else if (pass.equals(cfpass)){
                    if (db.checkUserExist(username))
                        Toast.makeText(RegisterActivity.this, "Username has existed", Toast.LENGTH_LONG).show();
                    else {
                        boolean insertSuccess = db.insertData(username, pass);
                        if (insertSuccess) {
                            Toast.makeText(RegisterActivity.this, "Signup successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    }
                }
                else
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
            }
        });

        tvAlreadyHaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }
}