package com.lukman.evomall.ui.cart

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.provider.SyncStateContract
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lukman.evomall.R
import com.lukman.evomall.data.local.CartModel
import com.lukman.evomall.databinding.FragmentCartBinding
import com.lukman.evomall.ui.CheckoutActivity
import com.lukman.evomall.ui.detail.DetailActivity
import com.lukman.evomall.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.zip.CheckedOutputStream

@AndroidEntryPoint
class CartFragment: Fragment(R.layout.fragment_cart) {
    private val viewModel: CartViewModel by viewModels()
    private val _viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentCartBinding?= null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCartBinding.bind(view)

        val adapter = CartAdapter(
            ::handletoDelete,
            ::handleToDetail
        )

        viewModel.product.observe(viewLifecycleOwner){
            adapter.setCartList(it)

            binding.apply {
                rvProduct.setHasFixedSize(true)
                rvProduct.adapter = adapter

                fbCheckOut.setOnClickListener {
                    val intent = Intent(requireContext(), CheckoutActivity::class.java)
                    startActivity(intent)
                    viewModel.deleteAllProduct()
                }
            }
        }

    }

    fun handletoDelete(item: CartModel){
        _viewModel.deleteProduct(item.id_product)
        Toast.makeText(requireContext(), "Successfully Delete Product", Toast.LENGTH_SHORT).show()
    }

    fun handleToDetail(item: CartModel){
        val intent = Intent(requireContext(), DetailActivity::class.java)
//        intent.putExtra("product", item)
        startActivity(intent)
    }
}