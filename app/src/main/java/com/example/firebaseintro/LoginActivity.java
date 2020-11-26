package com.example.firebaseintro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    //declare our elements
    TextInputEditText email,pass;
    Button btnLogin;
    //declare your Firebase Auth
    private FirebaseAuth auth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //intialize the firebase auth
        auth = FirebaseAuth.getInstance();
        //ref ur views
        email = findViewById(R.id.loginEmail);
        pass = findViewById(R.id.loginPass);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

        //onclicklistener for login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 login();
            }
        });
    }

    //method to login with firebase
    private void login() {
        //store the users input
        String login_email = email.getText().toString().trim();
        String login_pass = pass.getText().toString().trim();
        //check if the entries b4 login in the user
        if (TextUtils.isEmpty(login_email)){
            Toast.makeText(this, "Email field must not be empty", Toast.LENGTH_SHORT).show();
        } else {
            run(login_email,login_pass);
        }


        if (TextUtils.isEmpty(login_pass)){
            Toast.makeText(this, "Password field must not be empty", Toast.LENGTH_SHORT).show();
        } else {
            run(login_email,login_pass);
        }

    }

    //now all the data is there , login user
    private void run(String login_email,String login_pass){
        //show the progress bar
        progressBar.setVisibility(View.VISIBLE);
        //the method to signin a registered user on firebase project
        auth.signInWithEmailAndPassword(login_email,login_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Sorry check the credentials", Toast.LENGTH_SHORT).show();
                } else {
                    updateUi();
                }
            }
        });
    }

    private void updateUi() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    //link to forgot password page
    public void forgotPass(View v){
        Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
    //link to create Account
    public void createAccount(View v){
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}
