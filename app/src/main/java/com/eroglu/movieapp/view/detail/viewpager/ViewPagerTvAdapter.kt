package com.eroglu.movieapp.view.detail.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eroglu.movieapp.databinding.ItemViewPagerBinding
import com.eroglu.movieapp.model.tvSeries.TVSeriesResult
import com.eroglu.movieapp.util.Constants

interface ViewPagerItemClickedListenerTv {
    fun onItemClickedTv(item: TVSeriesResult)
}
class ViewPagerTvAdapter: RecyclerView.Adapter<ViewPagerTvAdapter.ViewPagerTvViewHolder>() {

    var itemClickedListenerTv: ViewPagerItemClickedListenerTv? = null
    inner class ViewPagerTvViewHolder(var binding: ItemViewPagerBinding) : RecyclerView.ViewHolder(binding.root)

    private val difUtil = object : DiffUtil.ItemCallback<TVSeriesResult>(){
        override fun areItemsTheSame(oldItem: TVSeriesResult, newItem: TVSeriesResult) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: TVSeriesResult, newItem: TVSeriesResult) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,difUtil)

    var viewPagerTvList: List<TVSeriesResult?>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerTvViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerTvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewPagerTvList.size
    }

    override fun onBindViewHolder(holder: ViewPagerTvViewHolder, position: Int) {
        holder.binding.viewPagerText.text = viewPagerTvList[position]?.originalName
        Glide
            .with(holder.binding.root.context)
            .load("${Constants.IMAGE_BASE_URL}${viewPagerTvList[position]?.backdropPath}")
            .centerCrop()
//            .placeholder(R.drawable.loading_spinner) // Resim yüklenene kadar gösterilecek olan.
            .into(holder.binding.viewPagerImage)
    }


}