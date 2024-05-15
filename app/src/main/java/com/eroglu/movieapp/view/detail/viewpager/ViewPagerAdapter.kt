package com.eroglu.movieapp.view.detail.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eroglu.movieapp.databinding.ItemViewPagerBinding
import com.eroglu.movieapp.model.movies.MovieResult
import com.eroglu.movieapp.util.Constants.IMAGE_BASE_URL

interface ViewPagerItemClickedListener{
    fun onItemClicked(item: MovieResult)
}
class ViewPagerAdapter: RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    var itemClickedListener: ViewPagerItemClickedListener? = null

    inner class ViewPagerViewHolder(var binding: ItemViewPagerBinding) : RecyclerView.ViewHolder(binding.root)

    private val difUtil = object : DiffUtil.ItemCallback<MovieResult>(){
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,difUtil)

    var viewPagerList: List<MovieResult?>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewPagerList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage = viewPagerList[position]
//        holder.binding.viewPagerImage.setImageResource(curImage)
        holder.binding.viewPagerText.text = viewPagerList[position]?.originalTitle
        Glide
            .with(holder.binding.root.context)
            .load("${IMAGE_BASE_URL}${viewPagerList[position]?.backdropPath}")
            .centerCrop()
//            .placeholder(R.drawable.loading_spinner) // Resim yüklenene kadar gösterilecek olan.
            .into(holder.binding.viewPagerImage)
    }
}