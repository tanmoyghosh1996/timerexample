package com.example.root.timerapplicationexample;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView tv_paper, tv_CurrentDate, tv_FinalDate;
    private TextView tv_balancename, days, hours, mins, seconds, btn_startexam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_paper = (TextView) findViewById(R.id.tv_paper);
        days = (TextView) findViewById(R.id.days);
        hours = (TextView) findViewById(R.id.hours);
        mins = (TextView) findViewById(R.id.mins);
        seconds = (TextView) findViewById(R.id.seconds);
        btn_startexam = (TextView) findViewById(R.id.btn_startexam);
        tv_balancename = (TextView) findViewById(R.id.tv_balancename);
        tv_CurrentDate = (TextView) findViewById(R.id.tv_CurrentDate);
        tv_FinalDate = (TextView) findViewById(R.id.tv_FinalDate);

        try {

            String CurrentDate = "2013/10/13 20:30:00";
            String FinalDate = "2013/10/13 20:31:00";
            tv_CurrentDate.setText("CurrentDate : "+CurrentDate);
            tv_FinalDate.setText("FinalDate : "+FinalDate);

            SimpleDateFormat dates = new SimpleDateFormat("yyyy/m/dd hh:mm:ss");

            Date date1 = dates.parse(CurrentDate);
            Date date2 = dates.parse(FinalDate);
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long daydifference = Math.abs(date1.getTime() - date2.getTime());
            tv_paper.setText(" " + difference);


            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = difference / daysInMilli;
            difference = difference % daysInMilli;

            long elapsedHours = difference / hoursInMilli;
            difference = difference % hoursInMilli;

            long elapsedMinutes = difference / minutesInMilli;
            difference = difference % minutesInMilli;

            long elapsedSeconds = difference / secondsInMilli;

            tv_balancename.setText(" day :" + elapsedDays + "  hour :" + elapsedHours + "  minutes :" + elapsedMinutes + "  second :" + elapsedSeconds);


            new CountDownTimer(daydifference, 1000) {
                public void onTick(long millisUntilFinished) {
                    btn_startexam.setVisibility(View.GONE);
                    days.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + " days  ");
                    hours.setText((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))) + " hours  ");
                    mins.setText((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + " mins  ");
                    seconds.setText((TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + " second left");
                }

                public void onFinish() {
                    btn_startexam.setVisibility(View.VISIBLE);
                    btn_startexam.setText("Start Exam");
                    days.setVisibility(View.GONE);
                    hours.setVisibility(View.GONE);
                    mins.setVisibility(View.GONE);
                    seconds.setVisibility(View.GONE);
                }

            }.start();


        } catch (Exception exception) {
            Log.e("DIDN'T WORK", "exception " + exception);
        }

    }
}
