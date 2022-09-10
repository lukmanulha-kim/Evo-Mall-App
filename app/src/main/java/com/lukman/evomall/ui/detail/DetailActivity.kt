package com.lukman.evomall.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lukman.evomall.R
import com.lukman.evomall.data.remote.Products
import com.lukman.evomall.databinding.ActivityDetailBinding
import com.lukman.evomall.databinding.FragmentProductsBinding
import com.lukman.evomall.ui.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.apply {
            tvDetailProductName.text = productDetail()?.title
            tvDetailProductDesc.text = productDetail()?.description
            tvDetailProductPrice.text = "$ "+productDetail()?.price

            Glide.with(this@DetailActivity)
                .load(productDetail()?.thumbnail)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(ivDetailProductImage)

            ivDetailAddtocart.setOnClickListener {
                productDetail()?.let { it1 -> viewModel.addToCart(it1) }
                Toast.makeText(this@DetailActivity, "Successfully Added Product", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun productDetail() = intent.getParcelableExtra<Products>("product")
}