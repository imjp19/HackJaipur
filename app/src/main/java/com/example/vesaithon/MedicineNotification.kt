package com.example.vesaithon

import android.app.IntentService
import android.content.Intent
import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MedicineNotification : IntentService("MedicineNotification") {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    override fun onHandleIntent(intent: Intent?) {
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        ref = database.getReference()   // Creating a reference to database url


    }

    fun checkForMedicines(){

    }


}
