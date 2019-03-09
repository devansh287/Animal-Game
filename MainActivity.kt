package com.example.cs193a_hw5_devansh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    companion object {
        const val FIREBASE_USERNAME = ""
        const val FIREBASE_PASSWORD = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        // connect to Firebase and log in
        mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(MainActivity.FIREBASE_USERNAME, MainActivity.FIREBASE_PASSWORD)


    }

    fun gamePlay (view: View)
    {
        val myIntent = Intent(this, PlayGameActivity::class.java)
        startActivity(myIntent)
    }
}
