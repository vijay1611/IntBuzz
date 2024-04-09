package com.example.intbuzz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.intbuzz.databinding.ActivityMainBinding
import com.example.intbuzz.databinding.ActivityTopicBinding

class TopicActivity : AppCompatActivity(),OnClickListener {

    lateinit var binding : ActivityTopicBinding
    lateinit var adapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.que1.setOnClickListener(this)
        binding.que2.setOnClickListener(this)
        binding.que3.setOnClickListener(this)
        binding.que4.setOnClickListener(this)
        binding.que5.setOnClickListener(this)


    }

    @SuppressLint("SuspiciousIndentation")
    override fun onClick(v: View?) {
         val intent =Intent(this,MainActivity::class.java)
             intent.putExtra("ans",v?.tag.toString())
             startActivity(intent)

       }
    }
