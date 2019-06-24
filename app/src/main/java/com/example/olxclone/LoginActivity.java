package com.example.olxclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.olxclone.entity.User;
import com.example.olxclone.service.Service;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private EditText edLogin_email;
    private EditText edLogin_password;
    private Button btnLogin_login;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.service = new Service();

        this.edLogin_email = findViewById(R.id.edLogin_email);
        this.edLogin_password = findViewById(R.id.edLogin_password);
        this.btnLogin_login = findViewById(R.id.btnLogin_login);

        this.btnLogin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(0, "", edLogin_email.getText().toString(), edLogin_password.getText().toString());
                try {

                    User nUser = service.login(user);
                    if(nUser != null) Toast.makeText(LoginActivity.this, nUser.getEmail(), Toast.LENGTH_LONG).show();
                    else Toast.makeText(LoginActivity.this, "Login FAIL", Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
