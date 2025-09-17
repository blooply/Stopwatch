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
    private lateinit var timer: Chronometer
    private lateinit var buttonStartStop: Button
    private lateinit var buttonReset: Button
    private var isRunning: Boolean = false
    private var pauseTime: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("MainActivity", "onCreate: ")
        wireWidgets()

        buttonStartStop.setOnClickListener {
            if (!isRunning) {
                isRunning = true
                buttonStartStop.text = "Stop"
                if (pauseTime != 0L) timer.base = pauseTime
                timer.start()
            }
            else {
                isRunning = false
                buttonStartStop.text = "Start"
                timer.stop()
                pauseTime = timer.base
            }
        }

        buttonReset.setOnClickListener {
            isRunning = false
            buttonStartStop.text = "Start"
            timer.stop()
            timer.base = SystemClock.elapsedRealtime()
            pauseTime = timer.base
        }
    }

    private fun wireWidgets() {
        timer = findViewById(R.id.chronometer_main_stopwatch)
        buttonStartStop = findViewById(R.id.button_main_startStop)
        buttonReset = findViewById(R.id.button_main_reset)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy: ")
    }
}