package com.navis.customviewexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

import com.navis.customviewexample.R;

public class MyEditText extends AppCompatEditText {

    Drawable mClearButton = null;

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }
    public MyEditText(Context context) {
        super(context);
        Init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    private void Init()
    {
        mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
        if (getText().toString().equals(""))
        {
            hideClearButton();
        }else
            showClearButton();

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = getText().toString();
                if (text.equals(""))
                {
                    hideClearButton();
                }else
                    showClearButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int tmp = getWidth() - getPaddingEnd() - mClearButton.getIntrinsicWidth();
                if (motionEvent.getX() >= tmp) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque, null);
                        showClearButton();
                    }
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        mClearButton = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
                        showClearButton();
                        getText().clear();
                    }
                }
                return true;
            };
        });
    }
    void showClearButton()
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButton, null);
    }
    void hideClearButton()
    {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
