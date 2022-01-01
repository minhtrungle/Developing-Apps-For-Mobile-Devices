package com.navis.customviewexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MyControl extends View {
    int mWidth = 0;
    int mHeight = 0;
    float mCurrent = 0;
    Paint backgroundPaint = null;
    Paint foregroundPaint = null;
    Handler mUIHandler = null;
    void Init()
    {
        mUIHandler = new android.os.Handler();
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(Color.GRAY);
        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(Color.BLACK);
        foregroundPaint.setTextSize(40);
        foregroundPaint.setTextAlign(Paint.Align.CENTER);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                mCurrent = mCurrent + 0.2f;
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        invalidate();
                    }
                });
            }
        }, 0, 1000);
    }
    public MyControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init();
    }
    public MyControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }
    public MyControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float size = Math.min(mWidth, mHeight);
        canvas.drawCircle(mWidth /2, mHeight / 2, 0.8f * size / 2, backgroundPaint);
        float outerRadius = 0.8f * size / 2 + 50;
        float innerRadius = 0.8f * size / 2 - 50;
        for (int i = 1;i <= 12;i++)
        {
            double x = Math.cos(i * Math.PI / 6 - Math.PI / 2) * outerRadius + mWidth / 2;
            double y = Math.sin(i * Math.PI / 6 - Math.PI / 2) * outerRadius + mHeight / 2;
            canvas.drawText(Integer.toString(i), (float)x, (float)y, foregroundPaint);
        }

        double x = Math.cos(mCurrent * Math.PI / 6 - Math.PI / 2) * innerRadius + mWidth / 2;
        double y = Math.sin(mCurrent * Math.PI / 6 - Math.PI / 2) * innerRadius + mHeight / 2;
        canvas.drawCircle((float)x, (float)y, 10.0f, foregroundPaint);
    }
}
