package com.example.intbuzz

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.intbuzz.databinding.TopicItemViewBinding


class TopicAdapter(private val list:List<Question>,val checkListener: checkListener,val isResult:Boolean=true) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>(){
    var selectedRadioButton: RadioButton? = null


    fun getQuestion():List<Question>{
        return list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = TopicItemViewBinding.inflate(inflater, parent, false)
        return TopicViewHolder(binding)

    }

    override fun getItemCount(): Int {
      //  Log.d("size***", list.size.toString())
        return list.size

    }
    @SuppressLint("ResourceType")
    private fun checkAnswer(option: RadioButton, isCorrect: Boolean) {
//        selectedRadioButton?.apply {
//            setBackgroundResource(Color.TRANSPARENT)
//        }
//        selectedRadioButton = option
        if (isCorrect) {
            option.setBackgroundResource(R.color.green)

        }
    }
    private fun clearSelection(holder: TopicViewHolder) {
        holder.option1.isChecked = false
        holder.option2.isChecked = false
        holder.option3.isChecked = false
        holder.option4.isChecked = false
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {

        holder.radioGroup.setOnCheckedChangeListener(null) // Remove listener temporarily
        holder.radioGroup.clearCheck() // Clear any existing checked state
        holder.itemQuestion.text = "${position + 1}. " + list[position].question
        holder.option1.text = list[position].options[0].option
        holder.option2.text = list[position].options[1].option
        holder.option3.text = list[position].options[2].option
        holder.option4.text = list[position].options[3].option

        holder.option1.isChecked = list[position].options[0].isSelected == true
        holder.option2.isChecked = list[position].options[1].isSelected == true
        holder.option3.isChecked = list[position].options[2].isSelected == true
        holder.option4.isChecked = list[position].options[3].isSelected == true
        if(isResult){
            checkAnswer(holder.option1,list[position].options[0].isCorrect)
            checkAnswer(holder.option2,list[position].options[1].isCorrect)
            checkAnswer(holder.option3,list[position].options[2].isCorrect)
            checkAnswer(holder.option4,list[position].options[3].isCorrect)
        }

        holder.radioGroup.setOnCheckedChangeListener { group, id ->
            if (id != -1) {
                when (id) {
                    R.id.option1 -> {
                        list[position].isCorrect = list[position].options[0].isCorrect
                        list[position].options[0].isSelected = true
                    }

                    R.id.option2 -> {
                        list[position].isCorrect = list[position].options[1].isCorrect
                        list[position].options[1].isSelected = true
                    }

                    R.id.option3 -> {
                        list[position].isCorrect = list[position].options[2].isCorrect
                        list[position].options[2].isSelected = true
                    }

                    R.id.option4 -> {
                        list[position].isCorrect = list[position].options[3].isCorrect
                        list[position].options[3].isSelected = true
                    }
                }
                checkListener.isAllAnswered(list.none{it.isCorrect==null})
            }
        }





        }

    inner class TopicViewHolder(private val binding: TopicItemViewBinding):RecyclerView.ViewHolder(binding.root){
        var itemQuestion = binding.que
        var option1 = binding.option1
        var option2 = binding.option2
        var option3 = binding.option3
        var option4 = binding.option4
        var radioGroup=binding.radioGroup


    }



}

interface checkListener{
    fun isAllAnswered(checked : Boolean)
}