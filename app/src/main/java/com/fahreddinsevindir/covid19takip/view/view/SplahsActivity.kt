package com.fahreddinsevindir.covid19takip.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fahreddinsevindir.covid19takip.R
import java.lang.Exception

class SplahsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splahs)

        val background = object :Thread(){
            override fun run() {
                try {
                    Thread.sleep(5000)
                    val intent = Intent(baseContext,
                        MainActivity::class.java)
                    startActivity(intent)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()


    }
}