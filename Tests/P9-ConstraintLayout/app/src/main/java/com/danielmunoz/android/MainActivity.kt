package com.danielmunoz.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var  first: EditText
    private lateinit var second: EditText
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        first  = findViewById(R.id.first_number)
        second = findViewById(R.id.second_number)
        result = findViewById(R.id.result)

        findViewById<Button>(R.id.add).setOnClickListener { add() }
        findViewById<Button>(R.id.clear).setOnClickListener { clear() }
    }

    private fun add() {
        val firstNumber  = first.text.toString().toIntOrNull()  ?: 0
        val secondNumber = second.text.toString().toIntOrNull() ?: 0
        result.text = getString(R.string.result, firstNumber + secondNumber)
    }

    private fun clear() {
        first.text.clear()
        second.text.clear()
        result.text = getString(R.string.default_result)
    }
}