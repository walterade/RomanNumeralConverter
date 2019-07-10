package com.walterade.romannumeralconverter.ui.input;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import com.walterade.romannumeralconverter.ui.base.BasePresenter;
import com.walterade.romannumeralconverter.util.RomanNumeralUtil;

class InputPresenter extends BasePresenter<InputView> {

    private static String ARG_ROMAN_NUMERAL = "romanNumeral";


    @Override
    public void restoreState(Bundle savedInstanceState) {
        getView().showRomanNumeral(savedInstanceState.getString(ARG_ROMAN_NUMERAL));
    }

    void saveState(Bundle outState, String romanNumeral) {
        outState.putString(ARG_ROMAN_NUMERAL, romanNumeral);
    }

    void convertToNumberClicked(String romanNumeral) {
        if (validateRomanNumeral(romanNumeral)) {
            getView().showConvertRomanNumeralToNumber(romanNumeral);
        }
    }

    boolean editorAction(String text, int actionId) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            convertToNumberClicked(text);
            return true;
        }
        return false;
    }

    private boolean validateRomanNumeral(String romanNumeral) {
        try {
            RomanNumeralUtil.convertToNumber(romanNumeral);
            getView().showError(null);
            return true;
        } catch (NumberFormatException e) {
            getView().showError(e.getMessage());
            return false;
        }
    }

}
