package com.example.instagramtry;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(App.this).applicationId("eoabgWQhoeaeXXL0gfeOjQT6S1gF1NTHhqmaX97f").clientKey("4XQmwUnDIiwHu7Ai6VnxHcFXm950iCcsRL6mKxic").server("https://parseapi.back4app.com/").build());
    }
}
