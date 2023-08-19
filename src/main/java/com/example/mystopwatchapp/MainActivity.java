package com.example.mystopwatchapp;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isRunning = false;
    private long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView timeTextView = findViewById(R.id.timeTextView);
        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStopwatch(timeTextView);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStopwatch();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStopwatch(timeTextView);
            }
        });
    }

    private void startStopwatch(final TextView timeTextView) {
        if (!isRunning) {
            startTime = SystemClock.elapsedRealtime();
            isRunning = true;
            updateTime(timeTextView);
        }
    }

    private void stopStopwatch() {
        isRunning = false;
    }

    private void resetStopwatch(final TextView timeTextView) {
        isRunning = false;
        startTime = 0;
        timeTextView.setText("00:00:00");
    }

    private void updateTime(final TextView timeTextView) {
        if (isRunning) {
            long currentTime = SystemClock.elapsedRealtime() - startTime;

            int hours = (int) (currentTime / 3600000);
            int minutes = (int) ((currentTime - hours * 3600000) / 60000);
            int seconds = (int) ((currentTime - hours * 3600000 - minutes * 60000) / 1000);

            String timeText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            timeTextView.setText(timeText);

            timeTextView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateTime(timeTextView);
                }
            }, 1000);
        }
    }
}
