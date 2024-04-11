package com.example.intbuzz

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intbuzz.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainActivity : AppCompatActivity(),checkListener{
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: TopicAdapter

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // var listOfTopics = listOf<String>("java","python","javascript")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
     //   Log.d("adapterValue", )


       var value = intent.getStringExtra("ans")
       var fileName =
           if(value=="Java"){
           "javaQuestions.json"
       }else if(value=="Python"){
               "pythonQuestions.json"
           }else if(value=="Flutter"){
               "flutter.json"
           }else if(value=="React Native"){
               "reactNative.json"
           }else{
               "kotlin.json"
           }
        binding.btnSubmit.isEnabled = false
        binding.textTopic.text = value+" Questions"
        val jsonValue = getJsonToAttributes(fileName)
        adapter = TopicAdapter(jsonValue.questions,this,false)

        Log.d("i***", value.toString())
        binding.rvMain.adapter = adapter
       binding.rvMain.layoutManager = LinearLayoutManager(this)

       // binding.btnSubmit.isClickable = adapter.getQuestion().size==10





            binding.btnSubmit.setOnClickListener {
              var score=Gson().toJson(adapter.getQuestion(),
                  object :TypeToken<ArrayList<Question>>() {}.type
              )
             var intent = Intent(this,ResultActivity::class.java)
                intent.putExtra("score",score)
                startActivity(intent)

            }



    }
    private fun getJsonToAttributes(fileName: String): QuestionModel {

        val jsonData = getJsonDataFromAsset(application, fileName)
        return Gson().fromJson(jsonData, QuestionModel::class.java)
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