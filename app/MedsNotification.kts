package com.example.hackjaipur
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.Time
import android.util.Base64
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add.*
import java.io.ByteArrayOutputStream
import com.google.android.gms.common.util.IOUtils.toByteArray
import android.widget.TimePicker
import android.app.TimePickerDialog

class PushNotifications : android.app.IntentService(PushNotifications :: class.simpleName){

    override fun onHandleIntent(workIntent: Intent) {

        val dataString = workIntent.dataString

    }

}