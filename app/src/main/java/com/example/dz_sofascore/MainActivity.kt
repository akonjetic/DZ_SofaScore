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

        zadaca1()

    }

    private fun zadaca1(){
        val showHideBtn = findViewById<Button>(R.id.showHideButton)
        val helloWorldTextView = findViewById<TextView>(R.id.helloWorldTextView)

        showHideBtn.setOnClickListener {
            if(showHideBtn.text.equals(getString(R.string.button_show))){
                helloWorldTextView.isVisible = true
                showHideBtn.text = getString(R.string.button_hide)
            }
            else{
                helloWorldTextView.isVisible = false
                showHideBtn.text = getString(R.string.button_show)
            }
        }
    }
}