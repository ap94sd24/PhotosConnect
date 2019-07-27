/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean signUpModeActive = true;
    TextView loginTextView;
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            signUp(view);
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginTextView ) {
            Button signUpBtn = (Button) findViewById(R.id.signUpBtn);

            if (signUpModeActive) {
                signUpModeActive = false;
                signUpBtn.setText("Login");
                loginTextView.setText("or, Sign Up");
            } else {
                signUpModeActive = true;
                signUpBtn.setText("Sign up");
                loginTextView.setText("or, Login");
            }
        }
    }

    public void  signUp(View view) {

     if (usernameEditText.getText().toString().matches("")  || passwordEditText.getText().toString().matches("")) {
       Toast.makeText(this, "A username and a password  are required.", Toast.LENGTH_SHORT).show();
     } else {
         if (signUpModeActive) {
             Log.i("DEBUG", "SIGN UP MODE");
             ParseUser user = new ParseUser();
             user.setUsername(usernameEditText.getText().toString());
             user.setPassword(passwordEditText.getText().toString());

             user.signUpInBackground(new SignUpCallback() {
                 @Override
                 public void done(ParseException e) {
                     if (e == null) {
                         Log.i("Sign up", "Success!");
                     } else {
                         Log.i("DEBUG", "WTF");
                         Log.i("DEBUG",  e.getMessage());
                         Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         } else {
             // login
             Log.i("DEBUG", "SIGN IN MODE");
             ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                 @Override
                 public void done(ParseUser user, ParseException e) {
                     if (user != null) {
                         Log.i("Login", "Success!");
                     } else {
                         Log.i("DEBUG", "WTF");
                         Log.i("DEBUG",  e.getMessage());
                         Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         }
     }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      loginTextView = (TextView) findViewById(R.id.loginTextView);
      loginTextView.setOnClickListener(this);
      usernameEditText = (EditText) findViewById(R.id.userNameEditText);
      passwordEditText = (EditText) findViewById(R.id.passwordEditText);
      ImageView logoImageView = (ImageView) findViewById(R.id.logoImageView);
      RelativeLayout backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);


      passwordEditText.setOnKeyListener(this);
    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}