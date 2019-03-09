package com.example.cs193a_hw5_devansh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoseGameActivity : AppCompatActivity()
{
    val mainDB = FirebaseDatabase.getInstance().reference
    val questions = mainDB.child("animalgame/nodes")
    var currentQ = questions.child("1")
    var currentQuestion: DBNode? = null
    var status = false
    var quesID = 0
    var ansID = 0


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lose_game)
        quesID = intent.getIntExtra("quesID", 0)
        ansID = intent.getIntExtra("ansID", 0)
        currentQ = questions.child("$quesID")
        processData()
    }

    fun processData ()
    {
        currentQ.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(data: DataSnapshot)
            {
                currentQuestion = data.getValue(DBNode::class.java)
            }
        })
    }

    fun yesClick (view: View)
    {
        status = true
    }

    fun addAnswer (answer: String, location: Int)
    {
        val newAns = questions.push()
        newAns.setValue("$location")
        newAns.child ("text").setValue(answer)
        newAns.child("id").setValue(location)
        newAns.child("type").setValue("answer")
    }

    fun addQuestion ()
    {
        val x = findViewById<EditText>(R.id.newQuestion)
        val question = x.text.toString()
        val ans = findViewById<EditText>(R.id.answer)
        val answer = ans.text.toString()
        var last = 0
        val query1 = questions.orderByKey().limitToLast(1)
        query1.addListenerForSingleValueEvent(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(data: DataSnapshot)
            {
                val lastData = data.getValue(DBNode::class.java)
                last = lastData!!.id
            }
        })

        val newQues = questions.push()
        last+=1
        newQues.setValue("$last")
        newQues.child("id").setValue(last)
        newQues.child("text").setValue(question)
        newQues.child("type").setValue("question")
        if (ansID == currentQuestion!!.yes)
        {
            currentQ.child("yes").setValue(last)
        }
        else
        {
            currentQ.child("no").setValue(last)
        }
        addAnswer(answer, last+1)
        if (status)
        {
            newQues.child("yes").setValue(last+1)
            newQues.child("no").setValue(ansID)
        }
        else
        {
            newQues.child("yes").setValue(ansID)
            newQues.child("no").setValue(last+1)
        }
    }

    fun mainmenu (view: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        addQuestion()
        startActivity(intent)
    }
}