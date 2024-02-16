package com.example.marketplace.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.Models.Tags
import com.example.marketplace.R
import com.example.marketplace.databinding.TagWheelBinding

class TagsAdapter(var tags:ArrayList<Tags>):RecyclerView.Adapter<TagsAdapter.TagViewHolder>() {

    inner class TagViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val binding = TagWheelBinding.bind(itemView)

        fun onBind(item:Tags){
            binding.tagName.text = item.tagName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.tag_wheel,parent,false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.onBind(tags[position])
    }

    override fun getItemCount(): Int = tags.size
}