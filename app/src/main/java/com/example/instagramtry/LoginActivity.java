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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {
    private ImageView imgZmaj;
    private EditText  edtUsername, edtPassword;
    private Button btnSignup, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imgZmaj=findViewById(R.id.imgZmaj);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        btnSignup= findViewById(R.id.btnSignup);
        btnLogin= findViewById(R.id.btnLogin);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {

                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if(e == null){
                                FancyToast.makeText(LoginActivity.this, user.getUsername() +"Is logged in successfully",FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();

                            } else {

                                FancyToast.makeText(LoginActivity.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR, true).show();
                            }

                        }
                    });
                }
                return  false;
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(                        edtUsername.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")){
                    FancyToast.makeText(LoginActivity.this,"Username and Password are required",FancyToast.LENGTH_SHORT,FancyToast.INFO, true).show();

                }else {
                    ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {

                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if (e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + "Is logged in successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();
                            } else {

                                FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }

                        }
                    });
                }

            }

        });
    }
    public void getLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e){
            e.printStackTrace();
        }
     }
    private void transitionToSocialMediaActivity(){
        Intent intent= new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
