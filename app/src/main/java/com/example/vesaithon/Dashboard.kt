package com.example.vesaithon

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var intent = Intent(this, MedicineNotification :: class.java)
        startService(intent)

    }

     fun loadAddActivity(view: View){
        var intent = Intent(this , Add :: class.java )
        startActivity(intent)
    }
}
