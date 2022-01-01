package com.navis.scroll_textview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

public class MyDatePickerDialog extends DatePickerDialog {
    int resultYear;
    int resultMonth;
    int resultDoM;

    public MyDatePickerDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
        resultYear = year;
        resultMonth = month;
        resultDoM = dayOfMonth;
        super.onDateChanged(view, year, month, dayOfMonth);
    }
}
