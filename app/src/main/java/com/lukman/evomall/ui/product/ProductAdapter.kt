package com.lukman.evomall.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lukman.evomall.R
import com.lukman.evomall.data.remote.Products
import com.lukman.evomall.databinding.ProductsItemBinding

class ProductAdapter(
    val handleToCart: ((Products)) -> Unit,
    val handleToDetail: ((Products)) -> Unit
): PagingDataAdapter<Products, ProductAdapter.ProductViewHolder>(COMPARATOR) {

    inner class ProductViewHolder(private val binding: ProductsItemBinding)
        :RecyclerView.ViewHolder(binding.root){
            fun bind(products: Products){
                with(binding){
                    Glide.with(itemView)
                        .load("${products.thumbnail}")
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(ivProductImage)

                    tvProductName.text = products.title
                    tvProductDesc.text = products.description
                    tvProductPrice.text ="$ "+products.price

                    ivAddtocart.setOnClickListener {
                        handleToCart.invoke(products)
                    }

                    actionToDetail.setOnClickListener {
                        handleToDetail.invoke(products)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            holder.bind(currentItem)
        }
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<Products>(){
            override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean =
                oldItem == newItem

        }
    }

}