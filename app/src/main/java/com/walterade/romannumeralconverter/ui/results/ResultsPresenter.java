package com.walterade.romannumeralconverter.ui.results;

import com.walterade.romannumeralconverter.ui.base.BasePresenter;
import com.walterade.romannumeralconverter.util.RomanNumeralUtil;

class ResultsPresenter extends BasePresenter<ResultsView> {

    public void setRomanNumeral(String romanNumeral) {
        int number = RomanNumeralUtil.convertToNumber(romanNumeral);

        getView().showRomanNumeral(romanNumeral);
        getView().showNumber(number);
    }

    public void convertAnotherClicked() {
        getView().showConvertAnotherRomanNumeral();
    }
}
