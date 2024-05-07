package com.eroglu.movieapp.view.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eroglu.movieapp.R


interface ItemClickedListener{
    fun onItemClicked(favoriteModel: FavoriteModel)
}
class FavoriteAdapter(private val favoriteList: List<FavoriteModel>,val clickListener: ItemClickedListener) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentItem = favoriteList[position]
        when(favoriteList[position].itemType?.name){
            FavoriteItemTypeEnum.MOVIES.name ->{
                holder.constraintLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.c1))
            }
            FavoriteItemTypeEnum.TV_SERIES.name ->{
                holder.constraintLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.c2))
            }
        }
        holder.tvTitle.text = currentItem.itemName
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${currentItem.itemPicture}")
            .apply(RequestOptions().centerCrop())
            .into(holder.ivMovieImage)

        holder.ivMovieImage.setOnClickListener{
            clickListener.onItemClicked(favoriteModel = favoriteList[position])
        }
    }

    override fun getItemCount() = favoriteList.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage: ImageView = itemView.findViewById(R.id.ivMovieImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }
}

