package com.example.usermanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

// TODO: 1. sound to text
//       2. adding droiduino code
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoLoginFragment();
    }

    private void gotoLoginFragment() {
        Intent intent = new Intent(this , ConnectBtActivity.class);
        startActivity(intent);
    }
}