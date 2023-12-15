package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.core.R
import com.example.core.databinding.ItemRowSearchuserBinding
import com.example.core.domain.model.User

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var listUser = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null

    fun setUser(newListUser : List<User>){
        listUser.clear()
        listUser.addAll(newListUser)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_searchuser, parent, false))


    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        val data = listUser[position]
        holder.bind(data)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val binding:ItemRowSearchuserBinding = ItemRowSearchuserBinding.bind(itemView)
        fun bind(data : User){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .circleCrop()
                    .into(imgItemPhoto)
                tvItemName.text = data.login
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listUser[adapterPosition])
            }
        }
    }

    override fun getItemCount() = listUser.size



}