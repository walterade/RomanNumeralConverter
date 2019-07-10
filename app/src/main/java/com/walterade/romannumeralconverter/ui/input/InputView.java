package com.walterade.romannumeralconverter.ui.input;

import com.walterade.romannumeralconverter.ui.base.BaseView;

interface InputView extends BaseView {
    void showConvertRomanNumeralToNumber(String romannumeral);
    void showError(String errorMessage);
    void showRomanNumeral(String romanNumeral);
}
