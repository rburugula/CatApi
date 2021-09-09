package com.example.dogapi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapi.databinding.CatBreedRowBinding
import com.example.dogapi.model.CatBreed
import com.squareup.picasso.Picasso

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var items = ArrayList<CatBreed>()
    var onItemClick: ((CatBreed) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CatBreedRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            Log.d("adapter", "item clicked")
            onItemClick?.invoke(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setUpdatedData(items: ArrayList<CatBreed>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: CatBreedRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CatBreed) {
            binding.tvName.text = data.name

            Picasso.get()
                .load(data.image.url)
                .into(binding.imageThumb)
        }
    }
}