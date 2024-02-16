package com.example.marketplace.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.Models.Tags
import com.example.marketplace.R
import com.example.marketplace.databinding.TagWheelBinding

class TagsAdapter(
    var tags:ArrayList<Tags>,
    var clickListener: ClickListener):RecyclerView.Adapter<TagsAdapter.TagViewHolder>() {

    inner class TagViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val binding = TagWheelBinding.bind(itemView)

        @SuppressLint("NotifyDataSetChanged")
        fun onBind(item:Tags){
            binding.tagName.text = item.tagName

            binding.root.setOnClickListener{
                clickListener.onClickListener(item)
            }
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
interface ClickListener{
    fun onClickListener(item: Tags)
}