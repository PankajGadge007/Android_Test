package com.first.admin.login_exercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText txtUserName,txtPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPwd);
        btnLogin=(Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {

        Toast.makeText(this,txtUserName.getText().toString() + txtPassword.getText().toString(),Toast.LENGTH_SHORT).show();
        View main = (View) findViewById(R.id.mainlayout);
        if (txtUserName.getText().toString().equals("android_123") && txtPassword.getText().toString().equals("google"))
        {


            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Login Successfull");
            alert.setPositiveButton("OK",null);


            //main.setBackgroundColor(Color.parseColor("#00FF00"));
            v.sett(Color.parseColor("#00FF00"));

            alert.show();




        }
        else
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Login Failed");
            alert.setPositiveButton("OK",null);


            main.setBackgroundColor(Color.parseColor("#FF0000"));


            alert.show();
        }


    }
}
