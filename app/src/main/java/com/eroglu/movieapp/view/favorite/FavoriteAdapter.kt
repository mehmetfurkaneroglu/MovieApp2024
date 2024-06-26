package com.eroglu.movieapp.view.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eroglu.movieapp.databinding.FavoriteItemBinding
import com.eroglu.movieapp.model.CommonModel
import com.eroglu.movieapp.model.FavoriteItemTypeEnum

interface FavoriteItemClickedListener {
    fun onItemClicked(commonModel: CommonModel)
}


class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    var itemClickedListener: FavoriteItemClickedListener? = null

    inner class FavoriteViewHolder(val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<CommonModel>() {
        override fun areItemsTheSame(oldItem: CommonModel, newItem: CommonModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CommonModel, newItem: CommonModel) =
            oldItem == newItem

    }

    private val listDiffer = AsyncListDiffer(this, diffUtil)
    var favoriteList: List<CommonModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoriteItemBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentItem = favoriteList[position]

        holder.binding.tvTitle.text = currentItem.itemName
        holder.binding.tvImdb.text = currentItem.itemImdb.toString()
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${currentItem.itemPicture}")
            .apply(RequestOptions().centerCrop())
            .into(holder.binding.ivMovieImage)

        when(currentItem.itemType){
            FavoriteItemTypeEnum.MOVIES ->{
//                holder.binding.constraintLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.c1))
            }
            FavoriteItemTypeEnum.TV_SERIES ->{
//                holder.binding.constraintLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.c2))
            }
            else -> {}
        }

        holder.itemView.setOnClickListener{
            itemClickedListener?.onItemClicked(currentItem)
        }

    }

}