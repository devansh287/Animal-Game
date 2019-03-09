package com.example.cs193a_hw5_devansh


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PlayGameActivity : AppCompatActivity()
{
    lateinit var mainDB: DatabaseReference
    lateinit var questions: DatabaseReference
    lateinit var currentQ: DatabaseReference
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

        mainDB = FirebaseDatabase.getInstance().reference.child("animalgame")
        questions = mainDB.child("nodes")
        currentQ = questions.child("1")

        id = 0


        updateQ()
    }

    fun processData (): Int
    {
        var x =0
        currentQ.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot)
            {
                val currentQuestion = data.getValue(DBNode::class.java)!!
                x=currentQuestion.id
            }
        })
        return x
    }

    fun updateQ ()
    {
        var question: String = ""
        currentQ.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot)
            {
                val currentQuestion = data.getValue(DBNode::class.java)!!
                question = currentQuestion.text
            }
        })
        val displayedQuestion = findViewById<TextView>(R.id.currentQuestion)
        displayedQuestion.text = question
    }

    fun responseYes (view: View)
    {
        var b = true
        currentQ.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot)
            {
                val currentQuestion = data.getValue(DBNode::class.java)!!
                if (currentQuestion!!.isQuestion)
                {
                    id = currentQuestion!!.id
                    val newID = currentQuestion!!.yes
                    currentQ = questions.child("$newID")
                    processData()
                    updateQ()
                }
                else
                {
                    b = false
                }
            }
        })

        if (!b)
        {
            val myIntent = Intent(this, WinGame::class.java)
            startActivity(myIntent)

        }
    }
    fun responseNo (view: View)
    {
        var b = true
        currentQ.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot)
            {
                val currentQuestion = data.getValue(DBNode::class.java)!!
                if (currentQuestion!!.isQuestion)
                {
                    id = currentQuestion!!.id
                    val newID = currentQuestion!!.no
                    currentQ = questions.child("$newID")
                    updateQ()
                }
                else
                {
                    b = false
                }
            }
        })
        if(!b)
        {
            val myIntent = Intent(this, LoseGameActivity::class.java)
            myIntent.putExtra("quesID", id)
            myIntent.putExtra("ansID", processData())
            startActivity(myIntent)
        }


    }


}
