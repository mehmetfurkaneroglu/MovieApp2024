package com.eroglu.movieapp.view.detail.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eroglu.movieapp.databinding.ItemViewPagerBinding
import com.eroglu.movieapp.model.CommonModel
import com.eroglu.movieapp.view.tvSeries.ItemClickedListener

class ViewPagerAdapter: RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    var itemClickedListener: ItemClickedListener? = null

    inner class ViewPagerViewHolder(var binding: ItemViewPagerBinding) : RecyclerView.ViewHolder(binding.root)

    private val difUtil = object : DiffUtil.ItemCallback<CommonModel>(){
        override fun areItemsTheSame(oldItem: CommonModel, newItem: CommonModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CommonModel, newItem: CommonModel) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,difUtil)

    var list: List<CommonModel?>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
//        val curImage = viewPagerList[position]
//        holder.binding.viewPagerImage.setImageResource(curImage)
//        holder.binding.viewPagerText.text = viewPagerList[position]?.originalTitle
//        holder.binding.viewPagerText.text = list[position]?.title
//        Glide
//            .with(holder.binding.root.context)
//            .load("${IMAGE_BASE_URL}${list[position]?.backdropPath}")
//            .centerCrop()
////            .placeholder(R.drawable.loading_spinner) // Resim yüklenene kadar gösterilecek olan.
//            .into(holder.binding.viewPagerImage)
        holder.binding.data = list[position]
        itemClickedListener?.let {
            holder.binding.callBack = it
        }
    }
}