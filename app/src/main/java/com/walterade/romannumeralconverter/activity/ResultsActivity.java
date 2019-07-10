package com.walterade.romannumeralconverter.activity;

import android.os.Bundle;

import com.walterade.romannumeralconverter.R;
import com.walterade.romannumeralconverter.ui.results.ResultsFragment;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    public static final String EXTRA_ROMAN_NUMERAL = "romanNumeral";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_results);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String romanNumeral = extras.getString(EXTRA_ROMAN_NUMERAL);

            ResultsFragment resultsFragment = ResultsFragment.newInstance(romanNumeral);
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, resultsFragment, ResultsFragment.TAG)
                .commit();
        }
    }
}
