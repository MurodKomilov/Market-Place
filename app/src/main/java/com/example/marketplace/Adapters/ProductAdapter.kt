package com.example.marketplace.Adapters

import android.graphics.Paint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.marketplace.Models.Products
import com.example.marketplace.R
import com.example.marketplace.databinding.ProductItemBinding
import com.example.myapplication.ViewPagerAdapter

class ProductAdapter(var product: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var onItemClick: ((Products) -> Unit)? = null

    fun setOnItemClickListener(listener: (Products) -> Unit) {
        onItemClick = listener
    }


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var binding = ProductItemBinding.bind(itemView)
        fun onBind(item: Products) {

            var imageList = mutableListOf<Int>()
            when (item.id) {
                1 -> {
                    imageList.add(R.drawable.photo6)
                    imageList.add(R.drawable.photo5)
                }
                2 -> {
                    imageList.add(R.drawable.photo1)
                    imageList.add(R.drawable.photo2)
                }
                3 -> {
                    imageList.add(R.drawable.photo5)
                    imageList.add(R.drawable.photo6)
                }
                4 -> {
                    imageList.add(R.drawable.photo3)
                    imageList.add(R.drawable.photo4)
                }
                5 -> {
                    imageList.add(R.drawable.photo2)
                    imageList.add(R.drawable.photo3)
                }
                6 -> {
                    imageList.add(R.drawable.photo6)
                    imageList.add(R.drawable.photo1)
                }
                7 -> {
                    imageList.add(R.drawable.photo4)
                    imageList.add(R.drawable.photo3)
                }
                8 -> {
                    imageList.add(R.drawable.photo1)
                    imageList.add(R.drawable.photo5)
                }
                else -> ""
            }

            binding.apply {
                imageVP.adapter = ViewPagerAdapter(imageList)
                imageVP.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                indicator.setViewPager(binding.imageVP)

                oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                productName.text = item.title
                subtitle.text = item.subtitle
                oldPrice.text = "${item.price.price} ₽"
                newPrice.text = "${item.price.priceWithDiscount} ₽"
                discount.text = "-${item.price.discount} %"
                ratingTv.text = "  ${item.feedback.rating}"
                reviews.text = "(${item.feedback.count})"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(product[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product[position])
        }

    }

    override fun getItemCount(): Int = product.size

}