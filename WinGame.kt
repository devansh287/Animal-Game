package com.example.cs193a_hw5_devansh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class WinGame : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win_game)
    }

    fun main (view: View)
    {
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }

}
