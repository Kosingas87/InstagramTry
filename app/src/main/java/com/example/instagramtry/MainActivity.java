package com.example.instagramtry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.security.Key;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgZmaj;
    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignup, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Zmajogram");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        imgZmaj=findViewById(R.id.imgZmaj);
        edtEmail=findViewById(R.id.edtEmail);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode== KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                onClick(btnSignup);
                }
                return false;
            }
        });
        btnSignup= findViewById(R.id.btnSignup);
        btnLogin= findViewById(R.id.btnLogin);
        btnSignup.setOnClickListener(MainActivity.this);
        btnLogin.setOnClickListener(MainActivity.this);
        if (ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final ParseUser appUser= new ParseUser();
//                appUser.setUsername(edtUsername.getText().toString());
//                appUser.setPassword(edtPassword.getText().toString());
//                appUser.setEmail(edtEmail.getText().toString());
//                final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
//                progressDialog.setMessage("Signing up "+edtUsername.getText().toString());
//                progressDialog.show();
//                if(edtEmail.getText().toString().equals("")||
//                        edtUsername.getText().toString().equals("") ||
//                        edtPassword.getText().toString().equals("")){
//                    FancyToast.makeText(MainActivity.this,"Email, Username and Password are required",FancyToast.LENGTH_SHORT,FancyToast.INFO, true).show();
//                    progressDialog.dismiss();
//                } else {
//
//                    appUser.signUpInBackground(new SignUpCallback() {
//                        @Override
//                        public void done(ParseException e) {
//                            if (e == null) {
//                                FancyToast.makeText(MainActivity.this, appUser.getUsername() + "Is signed up successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
//
//                            } else {
//                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
//                            }
//                            progressDialog.dismiss();
//                        }
//
//                    });
//                }
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnSignup:

                if(edtEmail.getText().toString().equals("")||
                        edtUsername.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this,"Email, Username and Password are required",FancyToast.LENGTH_SHORT,FancyToast.INFO, true).show();

                } else {
                    final ParseUser appUser= new ParseUser();
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());
                    appUser.setEmail(edtEmail.getText().toString());
                    final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Signing up "+edtUsername.getText().toString());
                    progressDialog.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, appUser.getUsername() + "Is signed up successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }

                        }

                    });
                    progressDialog.dismiss();

                }
                break;
            case R.id.btnLogin:
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitionToSocialMediaActivity(){
        Intent intent= new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
