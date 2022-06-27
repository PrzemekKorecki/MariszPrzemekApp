package com.example.mariszprzemekapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener   {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..5){
            println(i)
            Toast.makeText(this@MainActivity, "!!! !!! No weź coś wybierz !!! !!!", Toast.LENGTH_SHORT).show()
        }

        textView
            .setOnClickListener {
                val pass = "hej"
                val check = editTextTextPassword.text.toString()
                if (pass == check) {
                    editTextTextPassword.setText("")
                    startActivity(Intent(this, SecondActivity::class.java))
                } else {
                    Toast.makeText(this@MainActivity, "Wpisałeś: $check", Toast.LENGTH_LONG).show()
                    editTextTextPassword.setText("")
                    editTextTextPassword.hint = "Źle! Spróbuj jeszcze raz"
                    Handler().postDelayed({editTextTextPassword.hint = ""}, 5000)
                }
            }

        editTextTextPassword.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) editTextTextPassword.hint = ""
            else editTextTextPassword.hint = "Moja podpowiedź"
        }
        textView2.text = """| //
                           .|//
                           .|/ \""".trimMargin(".")


            textView2.setOnClickListener() { simpleAnalogClock.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate))
                Handler().postDelayed({
                    startActivity(Intent(this, MainActivity2::class.java))
                },5000)
            }

        val adapter = ArrayAdapter.createFromResource(this, R.array.animations, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(p0?.context, p0?.getItemAtPosition(p2).toString(), Toast.LENGTH_LONG).show()

        fun animacja(context: Context, anime:Int?){
            simpleAnalogClock.startAnimation(AnimationUtils.loadAnimation(context, anime!!))
        }

        val skroll = mapOf<Int, Int>   (1 to R.anim.bounce, 2 to R.anim.zoomin, 3 to R.anim.zoomout,
                                        4 to R.anim.movebottom, 5 to R.anim.movetop, 6 to R.anim.moveleft,
                                        7 to R.anim.moveright, 8 to R.anim.rotate, 9 to R.anim.fadein,
                                        10 to R.anim.fadeout, 11 to R.anim.blink, 12 to R.anim.slideup,
                                        13 to R.anim.slidedown, 14 to R.anim.slideleft, 15 to R.anim.slideright)

        when(p0?.getItemAtPosition(p2).toString()){
            "bounce" ->     animacja(this, skroll[1])
            "zoomin" -> {   animacja(this, skroll[2])
                            simpleAnalogClock.setBackgroundColor(Color.RED)
                            editTextTextPassword.hint = animacja(this, R.anim.zoomin).hashCode().toString()
                            Handler().postDelayed({editTextTextPassword.hint = ""}, 5000)}
            "zoomout" -> {  animacja(this, skroll[3])
                            editTextTextPassword.hint = animacja(this, R.anim.zoomout).hashCode().toString()}
            "movebottom" -> animacja(this, skroll[4])
            "movetop" ->    animacja(this, skroll[5])
            "moveleft" ->   animacja(this, skroll[6])
            "moveright" ->  animacja(this, skroll[7])
            "rotate" ->     animacja(this, skroll[8])
            "fadein" ->     animacja(this, skroll[9])
            "fadeout" ->    animacja(this, skroll[10])
            "blink" ->      animacja(this, skroll[11])
            "slideup" ->    animacja(this, skroll[12])
            "slidedown" ->  animacja(this, skroll[13])
            "slideleft" ->  animacja(this, skroll[14])
            "slideright" -> animacja(this, skroll[15])
            else -> Toast.makeText(this@MainActivity, "Wybierz może jednak coś innego !!!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO()
    }
}

//editText.requestFocus()
//editText.clearFocus()





