package com.walterade.romannumeralconverter.ui.input;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.walterade.romannumeralconverter.R;
import com.walterade.romannumeralconverter.activity.ResultsActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InputFragment extends Fragment implements InputView {

    public static final String TAG = "InputFragment";

    public static InputFragment newInstance() {
        return new InputFragment();
    }


    @BindView(R.id.roman_numeral)
    TextInputLayout romanNumeralTextInputLayout;

    @BindView(R.id.convert)
    Button convertButton;

    private InputPresenter inputPresenter = new InputPresenter();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_input, container, false);

        ButterKnife.bind(this, v);

        if (romanNumeralTextInputLayout.getEditText() != null) {
            romanNumeralTextInputLayout.getEditText().setOnEditorActionListener(
                (v1, actionId, event) ->
                        inputPresenter.editorAction(v1.getText().toString(), actionId));
        }

        inputPresenter.attachView(this, savedInstanceState);

        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (romanNumeralTextInputLayout.getEditText() != null)
            inputPresenter.saveState(outState, romanNumeralTextInputLayout.getEditText().getText().toString());
    }

    @OnClick(R.id.convert)
    void onConvertClicked(View v) {
        if (romanNumeralTextInputLayout.getEditText() != null)
            inputPresenter.convertToNumberClicked(romanNumeralTextInputLayout.getEditText().getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        inputPresenter.detachView();

        romanNumeralTextInputLayout = null;

        convertButton.setOnClickListener(null);
        convertButton = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        inputPresenter = null;
    }

    @Override
    public void showConvertRomanNumeralToNumber(String romanNumeral) {
        if (getContext() != null) {
            Intent intent = new Intent(getContext(), ResultsActivity.class);
            ActivityOptions options = ActivityOptions.makeScaleUpAnimation(convertButton, 0, 0, 0 , 0);

            intent.putExtra(ResultsActivity.EXTRA_ROMAN_NUMERAL, romanNumeral);
            startActivity(intent, options.toBundle());
        }
    }

    @Override
    public void showError(String errorMessage) {
        romanNumeralTextInputLayout.setError(errorMessage);
    }

    @Override
    public void showRomanNumeral(String romanNumeral) {
        if (romanNumeralTextInputLayout.getEditText() != null)
            romanNumeralTextInputLayout.getEditText().setText(romanNumeral);
    }
}
