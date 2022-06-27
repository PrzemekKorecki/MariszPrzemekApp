package com.example.mariszprzemekapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    private lateinit var myRef: com.google.firebase.database.DatabaseReference
    private lateinit var listOfItems: ArrayList<MessageApp>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val firebase:FirebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebase.getReference("MessageApp")
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val message = messageText.text.toString()
            val senderName = "Przem_k" //sender.text.toString()
            val date = Date().toString()
            val zonk: String = Date().toString()

            val firebaseInput = MessageApp(message, senderName, date, zonk)
            myRef.child("${Date().time}").setValue(firebaseInput)
            messageText.setText("")
            messageText.hint = "Wiadomość wysłano"
            sender.hint = ("Brawo!")

            Handler(Looper.getMainLooper()).postDelayed({
                sender.hint = "Przem_k"
                messageText.hint = "Wpisz tekst"
                                         }, 3000)
        }
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot){
                listOfItems = ArrayList()
                for (i in dataSnapshot.children){
                    val newRow = i.getValue(MessageApp::class.java)
                    listOfItems.add(newRow!!)
                }
                setupAdapter(listOfItems)
            }
        })
    }

    private fun setupAdapter(arrayData: ArrayList<MessageApp>){
        myRecyclerView.adapter = RecyclerAdapter(arrayData)
    }

}

