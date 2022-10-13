package com.tworoot2.loginscreen.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tworoot2.loginscreen.MainActivity;
import com.tworoot2.loginscreen.R;
import com.tworoot2.loginscreen.RetrofitAPIWork.Controller;
import com.tworoot2.loginscreen.RetrofitAPIWork.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {

    EditText password, email;
    TextView responseText;
    CardView login;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        responseText = findViewById(R.id.responseText);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        login = findViewById(R.id.login);

        progressDialog = new ProgressDialog(LoginPage.this);
        progressDialog.setTitle("Loading.....");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                processLogin();
            }
        });

        checkUserExistence();

    }


    private void processLogin() {

        String email1 = email.getText().toString();
        String password1 = password.getText().toString();

        Call<ResponseModel> call = Controller
                .getInstance()
                .getApi()
                .verifyUser(email1, password1);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {

                if (response.isSuccessful()) {
                    ResponseModel responseModel = response.body();

                    Log.d("ResponseMessage", response.message().toString());


                    SharedPreferences sharedPreferences = getSharedPreferences("loginCredentials", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email1);
                    editor.putString("password", password1);
                    editor.putString("token", responseModel != null ? responseModel.getUser().getToken() : null);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginPage.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();

                } else {
                    responseText.setVisibility(View.VISIBLE);
                    responseText.setText("Something went wrong");
                    responseText.setTextColor(Color.RED);
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                responseText.setVisibility(View.VISIBLE);
                responseText.setText("Something went wrong");
                responseText.setTextColor(Color.RED);
                Log.d("failureResponseMessage", t.getMessage());

            }
        });
    }

    private void checkUserExistence() {

        SharedPreferences sharedPreferences = getSharedPreferences("loginCredentials", MODE_PRIVATE);
        if (sharedPreferences.contains("token")) {
            Intent intent = new Intent(LoginPage.this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
        } else {
            responseText.setText("|");
            responseText.setTextColor(Color.RED);
        }

    }
}