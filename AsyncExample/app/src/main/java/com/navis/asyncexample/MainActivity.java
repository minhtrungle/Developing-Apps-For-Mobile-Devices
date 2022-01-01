package com.navis.asyncexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int n = 1000;
    int sum = 0;
    Random r = new Random(System.currentTimeMillis());
    Object sync = new Object();
    TextView textSum = null;
    UpdateThread updateThread = null;
    class MyTask extends AsyncTask<Void, Void, Integer>
    {
        @Override
        protected Integer doInBackground(Void... voids) {
            try {
                int executionTime = r.nextInt(10000) % 1000;
                Thread.sleep(executionTime);
                Log.d("ASYNCDEBUG", Long.toString(Thread.currentThread().getId()));
                sum += 1;
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return sum;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            textSum.setText(Integer.toString(integer));
        }
    }
    class UpdateThread extends Thread{
        TextView textView = null;
        Handler handler = null;
        UpdateThread(TextView tv)
        {
            textView = tv;
        }

        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    final String str = (String)msg.obj;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(str);
                            if (str.equals("5000"))
                            {
                                getLooper().quit();
                            }
                        }
                    });
                }
            };
            Looper.loop();
        }
    }

    Runnable threadCode = new Runnable() {
        void Increase()
        {
            synchronized (sync) {
                sum += 1;
            }
        }
        @Override
        public void run() {
            try {
                int executionTime = r.nextInt(10000) % 1000;
                Thread.sleep(executionTime);
                Log.d("ASYNCDEBUG", Long.toString(Thread.currentThread().getId()));
                Increase();
                Log.d("ASYNCDEBUG", Integer.toString(sum));
                Message msg = Message.obtain();
                msg.obj = Integer.toString(sum);
                updateThread.handler.sendMessage(msg);
            }catch (Exception e)
            {
                e.printStackTrace();
                Log.d("ASYNCEXCEPTION", e.getMessage());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textSum = findViewById(R.id.textSum);
        Log.d("ASYNCDEBUG", Long.toString(Thread.currentThread().getId()));
        updateThread = new UpdateThread(textSum);
        updateThread.start();
    }

    public void onStartClick(View v)
    {
        Log.d("ASYNCDEBUG", Long.toString(Thread.currentThread().getId()));
        for (int i = 0;i < n;i++)
        {
            //Thread t = new Thread(threadCode);
            //t.start();
            MyTask t = new MyTask();
            t.execute();
        }
    }
}
