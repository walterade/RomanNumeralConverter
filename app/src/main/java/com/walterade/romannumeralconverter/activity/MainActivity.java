package com.walterade.romannumeralconverter.activity;

import android.os.Bundle;

import com.walterade.romannumeralconverter.R;
import com.walterade.romannumeralconverter.ui.input.InputFragment;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        if (getSupportFragmentManager().findFragmentByTag(InputFragment.TAG) == null) {
            InputFragment inputFragment = InputFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, inputFragment, InputFragment.TAG)
                    .commit();
        }
    }
}
