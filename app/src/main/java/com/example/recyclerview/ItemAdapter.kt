package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // ViewHolder untuk memegang referensi ke elemen UI dari item card
    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val profileImage: ImageView = view.findViewById(R.id.profileImage)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val universityTextView: TextView = view.findViewById(R.id.universityTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.nameTextView.text = currentItem.name
        holder.universityTextView.text = currentItem.university
        holder.profileImage.setImageResource(currentItem.imageResId) // Menggunakan gambar dari resource
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

