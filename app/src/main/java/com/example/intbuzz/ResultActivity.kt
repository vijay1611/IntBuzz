package com.example.intbuzz

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intbuzz.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ResultActivity : AppCompatActivity(),checkListener {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: TopicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var score = intent.getStringExtra("score")
        Log.d("score123", score.toString())

        var result1:ArrayList<Question> = Gson().fromJson(score, object : TypeToken<List<Question>>() {}.type)

        var score1 = result1.filter {
            it.isCorrect==true
        }.size

        Log.d("res123", result1.toString())

        binding.textTopic.text = "Score :"+score1+"/10"
        binding.btnSubmit.visibility=View.GONE
        binding.titleSub.visibility=View.VISIBLE


        adapter = TopicAdapter(result1,this)

        //Log.d("i***", value.toString())
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)


    }
    private fun getJsonToAttributes(fileName: String): Question {

        val jsonData = getJsonDataFromAsset(application, fileName)
        return Gson().fromJson(jsonData, Question::class.java)
    }
    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun isAllAnswered(checked: Boolean) {
        binding.btnSubmit.isEnabled= checked
    }
}