package com.ds.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ds.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.text.NumberFormat.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    //Variavel que vai adicionar todos os listerners
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    fun calculateTip() {

        // toString é necessario ja que o costOfService é um editable
        val stringInTextField = binding.costOfService.text.toString()

        //Só é possivel converter em double se ele passar para string primeiro
        val cost = stringInTextField.toDouble()

        val selectedId = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }


        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        //Isso fornece um formatador numérico que pode ser usado para formatar os números como moedas.
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}