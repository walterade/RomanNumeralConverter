package com.walterade.romannumeralconverter.ui.results;

import com.walterade.romannumeralconverter.ui.base.BaseView;

interface ResultsView extends BaseView {
    void showConvertAnotherRomanNumeral();
    void showRomanNumeral(String romanNumeral);
    void showNumber(int number);
}
