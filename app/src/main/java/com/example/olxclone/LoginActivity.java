package com.example.olxclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olxclone.entity.User;
import com.example.olxclone.service.Service;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private TextView tvLogin_forgot_passoword;
    private TextView tvLogin_register;
    private TextView tvLogin_terms;

    private EditText edLogin_email;
    private EditText edLogin_password;
    private Button btnLogin_login;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Back button on the acationBar:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Show button
        getSupportActionBar().setHomeButtonEnabled(true); //Activate button

        this.service = new Service();

        this.tvLogin_forgot_passoword = findViewById(R.id.tvLogin_forgot_password);
        this.tvLogin_register = findViewById(R.id.tvLogin_register);

        this.tvLogin_terms = findViewById(R.id.tvlogin_terms);
        this.tvLogin_terms.setMovementMethod(LinkMovementMethod.getInstance());

        this.edLogin_email = findViewById(R.id.edLogin_email);
        this.edLogin_password = findViewById(R.id.edLogin_password);
        this.btnLogin_login = findViewById(R.id.btnLogin_enter);

        this.btnLogin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User(0, "", edLogin_email.getText().toString(), edLogin_password.getText().toString());
                try {

                    user = service.login(user);
                    if(user != null) {

                        Intent intent = new Intent();
                        intent.putExtra("resultUser", user);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else Toast.makeText(LoginActivity.this, "Login FAIL", Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            finish();
            return true;
        }
        return false;
    }
}
