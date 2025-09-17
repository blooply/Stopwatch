package com.example.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity"
        val STATE_IS_RUNNING = "running"
        val TIME_ELAPSED = "time"
    }

    private lateinit var timer: Chronometer
    private lateinit var buttonStartStop: Button
    private lateinit var buttonReset: Button
    private var isRunning: Boolean = false
    private var timeElapsed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG, "onCreate: ")
        wireWidgets()

        if (savedInstanceState != null) {
            isRunning = savedInstanceState.getBoolean(STATE_IS_RUNNING)
            timeElapsed = savedInstanceState.getLong(TIME_ELAPSED)

            if (isRunning) {
                buttonStartStop.text = "Stop"
                timer.base = SystemClock.elapsedRealtime() - timeElapsed
                timer.start()
            }
            else {
                buttonStartStop.text = "Resume"
                timer.base = SystemClock.elapsedRealtime() - timeElapsed
            }
        }

        buttonStartStop.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                buttonStartStop.text = "Stop"
                timer.base = SystemClock.elapsedRealtime() - timeElapsed
                timer.start()
            }
            else {
                isRunning = false
                buttonStartStop.text = "Resume"
                timer.stop()
                timeElapsed = SystemClock.elapsedRealtime() - timer.base
            }
        }

        buttonReset.setOnClickListener {
            timer.base = SystemClock.elapsedRealtime()
            timeElapsed = 0
            if (!isRunning) buttonStartStop.text = "Start"
        }
    }

    private fun wireWidgets() {
        timer = findViewById(R.id.chronometer_main_stopwatch)
        buttonStartStop = findViewById(R.id.button_main_startStop)
        buttonReset = findViewById(R.id.button_main_reset)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        timer.stop()
        outState.putBoolean(STATE_IS_RUNNING, isRunning)
        if (isRunning) timeElapsed = SystemClock.elapsedRealtime() - timer.base
        outState.putLong(TIME_ELAPSED, timeElapsed)

        super.onSaveInstanceState(outState)
    }
}