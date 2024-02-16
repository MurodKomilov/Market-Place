package com.example.marketplace.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
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

        @SuppressLint("NotifyDataSetChanged", "ResourceAsColor")
        fun onBind(item:Tags){
            binding.tagName.text = item.tagName
            if (item.isSelected) {
                binding.root.background.setColorFilter(0xFF52606D.toInt(), PorterDuff.Mode.SRC_ATOP)
                binding.tagName.setTextColor(Color.WHITE)
            } else {
                binding.root.background.setColorFilter(0xFFFFFFFF.toInt(), PorterDuff.Mode.SRC_ATOP)
                binding.tagName.setTextColor(Color.parseColor("#A0A1A3"))
            }

            binding.root.setOnClickListener {
                // Обработка выбора тега
                handleClick(adapterPosition)
            }
        }

        private fun handleClick(position: Int) {
            // Сброс всех предыдущих выбранных тегов
            tags.forEach { it.isSelected = false }
            // Установка выбранного тега
            tags[position].isSelected = true
            // Уведомление об изменении данных в адаптере
            notifyDataSetChanged()
            // Уведомление слушателя о выборе тега
            clickListener.onClickListener(tags[position])
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