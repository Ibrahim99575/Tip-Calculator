package com.example.tiptime

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost:Double = if(stringInTextField==""){
            0.0
        } else{
            stringInTextField.toDouble()
        }

        // If the cost is null or 0, then display 0 tip and exit this function early.
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
        }

        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
            R.id.amazing_service -> 0.20
            R.id.good_service -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        if (binding.roundupQuestion.isChecked) {
            tip = ceil(tip)
        }
        displayTip(tip)
    }
    private fun displayTip(tip : Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}