package com.lukman.evomall.ui.product

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.lukman.evomall.R
import com.lukman.evomall.data.remote.Products
import com.lukman.evomall.databinding.FragmentProductsBinding
import com.lukman.evomall.ui.detail.DetailActivity
import com.lukman.evomall.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment: Fragment(R.layout.fragment_products) {
    private val viewModel: ProductViewModel by viewModels()
    private val _viewModel: DetailViewModel by  viewModels()
    private var _binding: FragmentProductsBinding ?= null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProductsBinding.bind(view)

        val adapter = ProductAdapter(
            ::handleRedirecttoCart,
            ::handleRedirecttoDetail
        )

        binding.apply {
            rvProduct.setHasFixedSize(true)
            rvProduct.adapter = adapter
        }
        viewModel.fetchProduct().observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    fun handleRedirecttoCart(item: Products){
        _viewModel.addToCart(item)
        Toast.makeText(requireContext(), "Successfully Added Product", Toast.LENGTH_SHORT).show()
    }

    fun handleRedirecttoDetail(item: Products){
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("product", item)
        startActivity(intent)
    }
}