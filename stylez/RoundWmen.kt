@file:Suppress("DEPRECATION")

package com.abeed.stylez

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.abeed.stylez.databinding.ActivityRoundWmenBinding

class RoundWmen : AppCompatActivity() {
    lateinit var binding : ActivityRoundWmenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.status_green)
        binding = ActivityRoundWmenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#25D366")))

        binding.button.setOnClickListener {
            startActivity(Intent(this, RoundHairWomen::class.java))
        }

    }
}