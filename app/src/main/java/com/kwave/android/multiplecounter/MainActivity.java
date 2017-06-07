package com.kwave.android.multiplecounter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViews[] = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 4; i++) {
            // 텍스트로 아이디 가져오기
            int resId = getResources().getIdentifier("textView" + (i + 1), "id", getPackageName());
            textViews[i] = (TextView) findViewById(resId);
        }
        // 실행부
//        for(int i = 0; i<textViews.length;i++){
//            Counter counter = new Counter(textViews[i]);
//        }
        Counter counter1 = new Counter(textViews[0], this);
        Counter counter2 = new Counter(textViews[1], this);
        Counter counter3 = new Counter(textViews[2], this);
        Counter counter4 = new Counter(textViews[3], this);

        counter1.start();
        counter2.start();
        counter3.start();
        counter4.start();
    }
}
    class Counter  extends Thread{
        Activity context;
        TextView textView;
        int count = 0;
        public Counter(TextView textView, Activity context) {
            this.textView = textView;
            this.context = context;
        }
        @Override
        public void run(){

            for(int i=0; i<10; i++){
                // 서브 thread에서 UI를 조작하기 위해 로직을 Main Thread에 붙혀준다.
                count++;
                context.runOnUiThread(new Runnable(){
                                          public void run() {
                                              textView.setText(count+"");       // 여기만 메인 스레드에서 동작한다.
                                          }
                                      });
                Log.e("Count","============="+count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                this.textView.setText(count+"");

            }
        }

    }

