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
                binding.apply {    // Работает при нажатии на Тега
                    root.background.setColorFilter(0xFF52606D.toInt(), PorterDuff.Mode.SRC_ATOP)
                    tagName.setTextColor(Color.WHITE)
                    tagName.setText("${item.tagName}  ").toString()
                    tagName.setCompoundDrawablesRelativeWithIntrinsicBounds(       // Показывает иконку крестика
                        0, 0, R.drawable.ic_tag_clear, 0)
                }

            } else {
                binding.apply {
                    root.background.setColorFilter(0xFFF8F8F8.toInt(), PorterDuff.Mode.SRC_ATOP)
                    tagName.setTextColor(Color.parseColor("#A0A1A3"))
                    tagName.setCompoundDrawablesRelativeWithIntrinsicBounds(       // Показывает иконку крестика
                        0, 0, 0, 0) }

            }

            binding.root.setOnClickListener {
                handleClick(adapterPosition)             // Обработка выбора тега
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun handleClick(position: Int) {

            tags.forEach { it.isSelected = false }        // Сброс всех предыдущих выбранных тегов

            tags[position].isSelected = true              // Установка выбранного тега

            notifyDataSetChanged()                        // Уведомление об изменении данных в адаптере

            clickListener.onClickListener(tags[position]) // Уведомление слушателя о выборе тега
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