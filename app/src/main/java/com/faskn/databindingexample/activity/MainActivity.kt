package com.faskn.databindingexample.activity

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.faskn.databindingexample.BR
import com.faskn.databindingexample.R
import com.faskn.databindingexample.databinding.ActivityMainBinding
import com.faskn.databindingexample.model.Emotions

class MainActivity : AppCompatActivity() {

    private val emotionList by lazy { ArrayList<Emotions>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        emotionList.add(Emotions("Fall In Love", R.drawable.fallinlove, Color.parseColor("#DD2C00")))
        emotionList.add(Emotions("Happy", R.drawable.happy, Color.parseColor("#f57c00")))
        emotionList.add(Emotions("Sad", R.drawable.sad, Color.parseColor("#7b1fa2")))
        emotionList.add(Emotions("Sleepy", R.drawable.sleepy, Color.parseColor("#0277bd")))
        emotionList.add(Emotions("Suprised", R.drawable.suprised, Color.parseColor("#ffeb3b")))

        object : CountDownTimer(9999999999,1000){
            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Finished.",LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {
                val random: Int = (0..4).random()
                val emotion = emotionList[random]
                binding.setVariable(BR.emotions, emotion)
                binding.executePendingBindings()
            }

        }.start()
    }

}
