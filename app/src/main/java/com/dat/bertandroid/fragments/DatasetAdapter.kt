package com.dat.bertandroid.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dat.bertandroid.databinding.ItemDatasetBinding

class DatasetAdapter(
    private val dataSetTitle: List<String>,
    private val onSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<DatasetAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemDatasetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.tvDataTitle.setOnClickListener {
                onSelected.invoke(adapterPosition)
            }
        }

        fun bind(title: String) {
            with(binding) {
                tvDataTitle.text = title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDatasetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSetTitle[position])
    }

    override fun getItemCount() = dataSetTitle.size
}
