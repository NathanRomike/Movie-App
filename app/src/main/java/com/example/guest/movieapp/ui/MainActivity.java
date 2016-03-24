package com.example.guest.movieapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.guest.movieapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.searchEditText) EditText mSearchEditText;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.spinner) Spinner mSpinner;
    String spinnerText = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerText = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerText = "Movie";
            }
        });

        mSearchButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String searchInput = mSearchEditText.getText().toString();
        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        intent.putExtra("searchInput", searchInput);
        intent.putExtra("spinnerCategory", this.spinnerText);
        startActivity(intent);
    }
}
