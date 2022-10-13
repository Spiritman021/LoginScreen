package com.tworoot2.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tworoot2.loginscreen.Auth.LoginPage;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textToken;
    CardView logout;
    TextView email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textToken = (TextView) findViewById(R.id.textToken);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        logout = findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("loginCredentials", MODE_PRIVATE);
                sharedPreferences.edit().remove("email").commit();
                sharedPreferences.edit().remove("password").commit();
                sharedPreferences.edit().remove("token").commit();
                sharedPreferences.edit().apply();
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
                finishAffinity();

            }
        });
        checkUserExistence();

    }

    private void checkUserExistence() {

        SharedPreferences sharedPreferences = getSharedPreferences("loginCredentials", MODE_PRIVATE);
        if (sharedPreferences.contains("token")) {

            email.setText(sharedPreferences.getString("email", ""));
            password.setText(sharedPreferences.getString("password", ""));
            textToken.setText(sharedPreferences.getString("token", ""));

        } else {
            Intent intent = new Intent(MainActivity.this, LoginPage.class);
            startActivity(intent);
            finishAffinity();
        }

    }

}