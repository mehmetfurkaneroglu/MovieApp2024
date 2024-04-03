package com.eroglu.movieapp.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.R
import com.eroglu.movieapp.databinding.PopularMoviesItemBinding
import com.eroglu.movieapp.model.MovieResult

interface ItemClickedListener{
    fun onItemClicked(item: MovieResult)
}

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var itemClickedListener: ItemClickedListener? = null

    inner class ViewHolder(val binding: PopularMoviesItemBinding): RecyclerView.ViewHolder(binding.root)

    private val difUtil = object : DiffUtil.ItemCallback<MovieResult>(){
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,difUtil)

    var list : List<MovieResult?>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PopularMoviesItemBinding>(inflater,R.layout.popular_movies_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(BR.data,list[position])
        itemClickedListener?.let { holder.binding.setVariable(BR.callBack,it) }

        /*
        holder.binding.apply {
            Glide...
            diyerek manuel de kullanılabilir. databinding ve binding adapter kullandığımız için
            sadece setvariable diyerek geri kalan işlemleri bindingadapterda yaptık
        }

         */
    }


}