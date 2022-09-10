package com.lukman.evomall.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lukman.evomall.R
import com.lukman.evomall.data.local.CartModel
import com.lukman.evomall.data.remote.Products
import com.lukman.evomall.databinding.CartItemBinding
import com.lukman.evomall.databinding.ProductsItemBinding
import com.lukman.evomall.ui.cart.CartAdapter.CartViewHolder

class CartAdapter(
    val handleToDelete: ((CartModel)) -> Unit,
    val handleToDetail: ((CartModel)) -> Unit
) : RecyclerView.Adapter<CartViewHolder>() {

    private lateinit var list: List<CartModel>

    fun setCartList(list: List<CartModel>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class CartViewHolder(private val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cartModel: CartModel){
            with(binding){
                Glide.with(itemView)
                    .load("${cartModel.thumbnail}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivProductImage)

                tvProductName.text = cartModel.title
                tvProductDesc.text = cartModel.description
                tvProductPrice.text ="$ "+cartModel.price

                ivDelete.setOnClickListener {
                    handleToDelete.invoke(cartModel)
                }

                actionToDetail2.setOnClickListener {
                    handleToDetail.invoke(cartModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemCallBack{
        fun onITemClick(cartModel: CartModel)
    }
}