package com.example.guest.movieapp.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.movieapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.searchEditText) EditText mSearchEditText;
    @Bind(R.id.searchButton) Button mSearchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearchButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String searchInput = mSearchEditText.getText().toString();
        Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
        intent.putExtra("searchInput", searchInput);
        startActivity(intent);
    }
}

