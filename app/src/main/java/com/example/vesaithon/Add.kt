package com.example.vesaithon

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
class Add : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val TAKE_PHOTO = 1
    private var encodedImage: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        ref = database.getReference()   // Creating a reference to database url

    }

    fun addMedicine(view: View) {
        val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val uid: String = currentUser!!.uid
        var name = medicineTextBox2.text.toString()
        var mornDosage = (morningdosageTextBox2.text.toString()).toInt()
        var noonDosage = (afternoondosageTextBox2.text.toString()).toInt()
        var nightDosage = (nightdosageTextBox.text.toString()).toInt()
        var mornTime = morningTextBox.text.toString()
        var noonTime = afternoonTextBox.text.toString()
        var nightTime = nightTextBox2.text.toString()

        if (name.isEmpty() || mornTime.isEmpty() || noonTime.isEmpty() || nightTime.isEmpty()) {        // in case any field is empty
            Toast.makeText(baseContext, "Please Fill all the empty fields", Toast.LENGTH_SHORT).show()
        }
        else {                              // Upload data to database
            uploadMedicineData(
                uid,
                encodedImage,
                name,
                mornDosage,
                noonDosage,
                nightDosage,
                mornTime,
                noonTime,
                nightTime
            )

            Toast.makeText(baseContext, "Medicine Added Successfully",Toast.LENGTH_SHORT).show()

            if(checkBox.isChecked){
                val intent = Intent(this, Add :: class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, Dashboard :: class.java)
                startActivity(intent)
            }
        }
    }

    fun uploadMedicineData(uid: String, encodedImage: String, name: String, mornDosage: Int, noonDosage: Int, nightDosage: Int, mornTime: String, noonTime: String, nightTime: String ){

        /*  MEDICINE DATABASE STRUCTURE
        *       VESAITHON
        *           MedicineData
        *               UID
        *                   name
        *                   encodedimage
        *                   mornTime
        *                   mornDosage
        *                   noonTime
        *                   noonDosage
        *                   nightTime
        *                   nightDosage
        *
        *
        * */

        ref.child("MedicineData").child(uid).child("name").setValue(name)
        ref.child("MedicineData").child(uid).child("encodedImage").setValue(encodedImage)
        ref.child("MedicineData").child(uid).child("morningDosage").setValue(mornDosage)
        ref.child("MedicineData").child(uid).child("afernoonDosage").setValue(noonDosage)
        ref.child("MedicineData").child(uid).child("nightDosage").setValue(nightDosage)
        ref.child("MedicineData").child(uid).child("morningTime").setValue(mornTime)
        ref.child("MedicineData").child(uid).child("afternoonTime").setValue(noonTime)
        ref.child("MedicineData").child(uid).child("nightTime").setValue(nightTime)
    }

    fun takePhoto(view: View) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, TAKE_PHOTO)

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {       // Displays taken image photo and encodes it to BASE64 String
        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)

            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 50, baos)
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos) //bm is the bitmap object
            val b = baos.toByteArray()
            //String encodedImage = Base64.encode(b, Base64.DEFAULT);
            encodedImage = Base64.encodeToString(b , Base64.DEFAULT)
        }
    }



}
