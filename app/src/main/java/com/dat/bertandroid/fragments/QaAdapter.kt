package com.dat.bertandroid.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dat.bertandroid.databinding.ItemQuestionBinding
//import  com.dat.bertandroid.databinding.ItemQuestionBinding

class QaAdapter(private val question: List<String>, private val select: (Int) -> Unit) :
    RecyclerView.Adapter<QaAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.tvQuestionSuggestion.setOnClickListener {
                select.invoke(adapterPosition)
            }
        }

        fun bind(question: String) {
            binding.tvQuestionSuggestion.text = question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(question[position])
    }

    override fun getItemCount() = question.size
}
