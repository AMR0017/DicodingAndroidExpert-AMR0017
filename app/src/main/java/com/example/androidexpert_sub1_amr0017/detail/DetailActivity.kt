package com.example.androidexpert_sub1_amr0017.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.androidexpert_sub1_amr0017.R
import com.example.androidexpert_sub1_amr0017.databinding.ActivityDetailBinding
import com.example.core.data.Resource
import com.example.core.domain.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel:DetailViewModel by viewModel()
    private lateinit var detailUser: User
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailUser = intent.getParcelableExtra(EXTRA_DATA)!!

        supportActionBar?.title = resources.getString(
            R.string.username_detail,
            detailUser.login
        )
        Glide.with(this)
            .load(detailUser.avatarUrl)
            .circleCrop()
            .into(binding.imgDetailPhoto)

        detailViewModel.getDetailUser(detailUser.login).observe(this) { users ->
            if (users != null){
                when (users) {
                    is Resource.Loading -> binding.progressBar3.visibility = View.VISIBLE

                    is Resource.Success -> {
                        binding.progressBar3.visibility = View.GONE
                        binding.apply {
                            tvDetailName.text = users.data?.name
                            tvDetailUsername.text = users.data?.login
                            if (users.data?.company != null) {
                                tvDetailCompany.text = users.data?.company
                            } else {
                                tvDetailCompany.text = "-"
                            }
                            if (users.data?.location != null) {
                                tvDetailLocation.text = users.data?.location
                            } else {
                                tvDetailLocation.text = "-"
                            }
                            tvDetailFollowers.text = users.data?.followers.toString()
                            tvDetailFollowing.text = users.data?.following.toString()
                            tvDetailRepository.text = users.data?.publicRepos.toString()
                        }



                    }

                    is Resource.Error -> {
                        binding.progressBar3.visibility = View.GONE
                    }

                    else -> {}
                }
            }
        }
        detailViewModel.checkFav(detailUser.login).observe(this){fav->
            binding.ivfavorite.setOnClickListener {
                detailViewModel.setFav(detailUser, fav)

            }
            setStatusFav(fav)
        }
    }


    private fun setStatusFav(status: Boolean){
        if(status){
            binding.ivfavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        }
        else{
            binding.ivfavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }



    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}