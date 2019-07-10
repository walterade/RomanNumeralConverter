package com.walterade.romannumeralconverter.ui.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.walterade.romannumeralconverter.R;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ResultsFragment extends Fragment implements ResultsView {

    public static final String TAG = "ResultsFragment";
    public static final String ARG_ROMAN_NUMERAL = "romanNumeral";

    public static ResultsFragment newInstance(String romanNumeral) {
        ResultsFragment f = new ResultsFragment();
        Bundle args = new Bundle();

        args.putString(ResultsFragment.ARG_ROMAN_NUMERAL, romanNumeral);
        f.setArguments(args);

        return f;
    }

    @BindView(R.id.roman_numeral)
    TextView romanNumeralTextView;

    @BindView(R.id.number)
    TextView numberTextView;

    @BindView(R.id.convert_another)
    Button convertAnother;

    private String romanNumeral;

    private ResultsPresenter resultsPresenter = new ResultsPresenter();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_results, container, false);

        ButterKnife.bind(this, v);

        Bundle args = getArguments();
        if (args != null) {
            romanNumeral = args.getString(ARG_ROMAN_NUMERAL);
        }

        resultsPresenter.attachView(this);
        resultsPresenter.setRomanNumeral(romanNumeral);

        return v;
    }

    @OnClick(R.id.convert_another)
    void onConvertAnotherClicked(View v) {
        resultsPresenter.convertAnotherClicked();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        resultsPresenter.detachView();

        romanNumeralTextView = null;
        numberTextView = null;

        convertAnother.setOnClickListener(null);
        convertAnother = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        resultsPresenter = null;
    }

    @Override
    public void showConvertAnotherRomanNumeral() {
        if (getActivity() != null) getActivity().supportFinishAfterTransition();
    }

    @Override
    public void showRomanNumeral(String romanNumeral) {
        romanNumeralTextView.setText(romanNumeral.toUpperCase(Locale.US));
    }

    @Override
    public void showNumber(int number) {
        numberTextView.setText(String.valueOf(number));
    }
}
