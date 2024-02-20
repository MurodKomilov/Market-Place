package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.databinding.ImageVpBinding

class ViewPagerAdapter(var image:List<Int>):RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {


    inner class Pager2ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var image:ImageView = itemView.findViewById(R.id.image)

        init {
            image.setOnClickListener {v: View ->
                val position = adapterPosition
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.image_vp,parent,false)
        return Pager2ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.image.setImageResource(image[position])
    }

    override fun getItemCount(): Int= image.size

}