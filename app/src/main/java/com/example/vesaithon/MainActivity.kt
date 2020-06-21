package com.example.vesaithon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var gso: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()


     }

    fun authenticateUser(view: View){
        val username = loginTextBox.text.toString()
        val password = passwordTextBox3.text.toString()
        var intent = Intent(this , Dashboard :: class.java)

        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(baseContext, "Authentication Success.", Toast.LENGTH_SHORT).show()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun resetPassword(view: View ){
        var email: String = loginTextBox.text.toString()
        auth!!.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "A password reset email has been sent to your entered email address", Toast.LENGTH_SHORT)
                } else {
                    Toast.makeText(baseContext, "Some error Occured", Toast.LENGTH_SHORT)
                }
            }
    }

    fun loadRegisterActivity(view: View){
        val intent = Intent(this , Register :: class.java)    // intent signifies the activity to be loaded
        startActivity(intent)   // load the intended activity
    }

    fun googleSignIn(view: View){
            // TODO: CONFIGURE GOOGLE SIGN IN
    }
}
