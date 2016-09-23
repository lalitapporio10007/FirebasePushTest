package com.example.apporioinfolabs.firebasepushtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class default_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_activity);

        Toast.makeText(default_activity.this, "Successss", Toast.LENGTH_SHORT).show();

    }
}
