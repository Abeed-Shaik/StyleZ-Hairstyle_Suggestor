@file:Suppress("DEPRECATION")

package com.abeed.stylez

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.abeed.stylez.databinding.ActivityMeasurementWomenBinding
import com.abeed.stylez.databinding.AlertmenmsrmntBinding
import com.abeed.stylez.databinding.AlertwmenmsrmntBinding

class MeasurementWomen : AppCompatActivity() {
    lateinit var binding : ActivityMeasurementWomenBinding

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tip_women, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.tipwomen) {
            startActivity(Intent(this@MeasurementWomen, MeasureTipWomen::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.status_green)
        binding = ActivityMeasurementWomenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#25D366")))

        binding.button2.setOnClickListener {

            val fhs = binding.fh.text.toString()
            val fls = binding.fl.text.toString()
            val cbs = binding.cb.text.toString()
            val jls = binding.jl.text.toString()

            if (fhs.isEmpty()||fls.isEmpty()||cbs.isEmpty()||jls.isEmpty()){
                val view: View = LayoutInflater.from(this).inflate(R.layout.alertmenmsrmnt, null)
                val binding: AlertmenmsrmntBinding = AlertmenmsrmntBinding.bind(view)
                val dialog: AlertDialog = AlertDialog.Builder(this)
                    .setTitle(HtmlCompat.fromHtml("<font color='#E30000'>WARNING</font>", HtmlCompat.FROM_HTML_MODE_LEGACY))
                    .setMessage("All fields must be provided with an input, no field is left Empty!!")
                    .setView(binding.root)
                    .create()
                binding.okmen.setOnClickListener { dialog.dismiss() }
                dialog.show()
            } else {

                val fh: Float = fhs.toFloat()
                val fl: Float = fls.toFloat()
                val cb: Float = cbs.toFloat()
                val jl: Float = jls.toFloat()

                if (((fh>4)&&(fh<26))&&((fl>4)&&(fl<31))&&((cb>4)&&(cb<31))&&((jl>3)&&(jl<26))){

                    if ((cb<(fl+2)&&cb>(fl-2))&&(fh<(jl+2)&&fh>(jl-2))&&(cb>fh)&&(cb>fh)&&(cb>jl)&&(fl>fh)&&(fl>jl)){

                        startActivity(Intent(this, RoundWmen::class.java))

                    } else if ((fl<(cb+2)&&fl>(cb-2))&&(fh<(cb+2)&&fh>(cb-2))&&(jl<(cb+2)&&jl>(cb-2))){

                        startActivity(Intent(this, SquareWmen::class.java))

                    } else if ((fh<(cb+2)&&fh>(cb-2))&&(jl<(cb+2)&&jl>(cb-2))&&(fl>cb)){

                        startActivity(Intent(this, LongWmen::class.java))

                    } else if ((fl>cb)&&(fh>jl)&&(fl>(((3.0/2.0)*(cb))-1))&&(fl<(((3.0/2.0)*(cb))+1))){

                        startActivity(Intent(this, OvalWmen::class.java))

                    } else if (((fh>cb)||(fh<(cb+2)&&fh>(cb-2)))&&(cb>jl)){

                        startActivity(Intent(this, HeartWmen::class.java))

                    } else if (fl>cb && cb>fh && fh>jl){

                        startActivity(Intent(this, DiamondWomen::class.java))

                    } else {

                        Toast.makeText(this, "Please enter exact measurements of your face", Toast.LENGTH_LONG).show()

                    }

                } else {
                    val view: View = LayoutInflater.from(this).inflate(R.layout.alertwmenmsrmnt, null)
                    val binding: AlertwmenmsrmntBinding = AlertwmenmsrmntBinding.bind(view)
                    val dialog: AlertDialog = AlertDialog.Builder(this)
                        .setTitle(HtmlCompat.fromHtml("<font color='#E30000'>WARNING</font>", HtmlCompat.FROM_HTML_MODE_LEGACY))
                        .setMessage("Measurements are either Exceeding it's max length or Subceeding it's min length.")
                        .setView(binding.root)
                        .create()
                    binding.okwmen.setOnClickListener { dialog.dismiss() }
                    dialog.show()

                }
            }

        }

    }
}