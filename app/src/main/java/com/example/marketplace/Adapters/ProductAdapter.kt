package com.example.marketplace.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.Models.Products
import com.example.marketplace.R
import com.example.marketplace.databinding.ProductItemBinding

class ProductAdapter(
    var product:List<Products>,
    var clickListener: ClickListener):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var binding = ProductItemBinding.bind(itemView)

        fun onBind(item:Products){



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ProductViewHolder(view)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(product[position])
    }

    override fun getItemCount(): Int= product.size

}