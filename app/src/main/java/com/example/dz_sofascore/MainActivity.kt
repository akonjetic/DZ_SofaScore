package com.example.dz_sofascore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showHideBtn = findViewById<Button>(R.id.showHideButton)
        val helloWorldTextView = findViewById<TextView>(R.id.helloWorldTextView)

        showHideBtn.setOnClickListener {
            if(showHideBtn.text.equals("Show Text")){
                helloWorldTextView.isVisible = true
                showHideBtn.text = "Hide Text"
            }
            else{
                helloWorldTextView.isVisible = false
                showHideBtn.text = "Show Text"
            }
        }
    }
}