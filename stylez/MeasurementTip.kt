package com.abeed.stylez

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abeed.stylez.databinding.ActivityMeasurementTipBinding

class MeasurementTip : AppCompatActivity() {
    lateinit var binding : ActivityMeasurementTipBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_StyleZ_Msr)
        binding = ActivityMeasurementTipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))
    }
}