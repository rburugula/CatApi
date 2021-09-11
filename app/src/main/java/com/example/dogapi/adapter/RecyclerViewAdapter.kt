package com.example.dogapi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapi.databinding.CatBreedRowBinding
import com.example.dogapi.model.CatBreed
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(), Filterable {

    private var items = ArrayList<CatBreed>()
    var filteredItems = ArrayList<CatBreed>()
    var onItemClick: ((CatBreed) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CatBreedRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(filteredItems[position])
        holder.itemView.setOnClickListener {
            Log.d("adapter", "item clicked")
            onItemClick?.invoke(filteredItems[position])
        }
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    fun setUpdatedData(items: ArrayList<CatBreed>) {
        this.items = items
        this.filteredItems = this.items
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                filteredItems = if (charString.isEmpty()) items else {
                    val filteredList = ArrayList<CatBreed>()
                    items
                        .filter {
                            (it.name.toLowerCase(Locale.ROOT).contains(
                                constraint!!.toString().toLowerCase(
                                    Locale.ROOT
                                )
                            ))
                        }
                        .forEach { filteredList.add(it) }
                    filteredList
                }
                return FilterResults().apply { values = filteredItems }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredItems = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<CatBreed>
                notifyDataSetChanged()
            }
        }
    }
}