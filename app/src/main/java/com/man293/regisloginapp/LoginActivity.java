package com.man293.regisloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPass;
    Button btnLogin;
    TextView tvNewAcc;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.inputUserName);
        etPass = findViewById(R.id.inputPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvNewAcc = findViewById(R.id.titleNewAcc);
        db = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean loginSuccess = db.checkLogin(etUsername.getText().toString(), etPass.getText().toString());
                if (loginSuccess) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("username", etUsername.getText().toString());
                    startActivity(i);
                }
                else
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
            }

        });

        tvNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}