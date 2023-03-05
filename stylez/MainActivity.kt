@file:Suppress("DEPRECATION")

package com.abeed.stylez

import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abeed.stylez.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

class MainActivity : AppCompatActivity() {

    private val updatecode123 = 22
    private var appUpdateManager: AppUpdateManager? = null

    lateinit var binding : ActivityMainBinding

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.aboutapp) {
            startActivity(Intent(this, AboutActivity::class.java))
            return true
        }
        if (id == R.id.policy) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://asitro.blogspot.com/2022/12/stylez-privacy-policy.html")
            startActivity(intent)
            return true
        }
        if (id == R.id.share) {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, "StyleZ")
                intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=" + applicationContext.packageName)
                startActivity(Intent.createChooser(intent, "Share With"))
            } catch (e: Exception) {
                Toast.makeText(this, "Unable to share this app.", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.black)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#25D366")))

        inAppUp()

        binding.men.setOnClickListener {
            startActivity(Intent(this, MeasurementMen::class.java))
        }

        binding.women.setOnClickListener {
            startActivity(Intent(this, MeasurementWomen::class.java))
        }
    }

    private fun inAppUp() {
        appUpdateManager = AppUpdateManagerFactory.create(this)
        val task = appUpdateManager!!.appUpdateInfo
        task.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                try {
                    appUpdateManager!!.startUpdateFlowForResult(appUpdateInfo,AppUpdateType.FLEXIBLE,this,updatecode123)
                } catch (e: SendIntentException) {
                    e.printStackTrace()
                    Log.d("update error", "onSuccess: $e")
                }
            }
        }
        appUpdateManager!!.registerListener(listener)
    }

    private var listener = InstallStateUpdatedListener { installState: InstallState ->
            if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                popUp()
            }
        }

    private fun popUp() {
        val snackbar = Snackbar.make(findViewById(android.R.id.content),"App Update Almost Done.",Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Reload") { appUpdateManager!!.completeUpdate() }
        snackbar.setActionTextColor(Color.parseColor("#FF0000"))
        snackbar.show()
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == updatecode123) {
            if (resultCode != RESULT_OK) {
                Log.e("error download", "onActivityResult: app download failed")
            }
        }
    }


}