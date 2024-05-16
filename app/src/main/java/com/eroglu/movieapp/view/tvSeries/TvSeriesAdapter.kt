package com.eroglu.movieapp.view.tvSeries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.R
import com.eroglu.movieapp.databinding.TvSeriesItemBinding
import com.eroglu.movieapp.model.tvSeries.TVSeriesResult

class TvSeriesAdapter: RecyclerView.Adapter<TvSeriesAdapter.ViewHolder>() {

    var itemClickedListenerTv: ItemClickedListener? = null

    inner class ViewHolder(val binding: TvSeriesItemBinding): RecyclerView.ViewHolder(binding.root)

    private val difUtil = object : DiffUtil.ItemCallback<TVSeriesResult>(){
        override fun areItemsTheSame(oldItem: TVSeriesResult, newItem: TVSeriesResult) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: TVSeriesResult, newItem: TVSeriesResult) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this,difUtil)

    var list : List<TVSeriesResult?>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<TvSeriesItemBinding>(inflater,R.layout.tv_series_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TvSeriesAdapter.ViewHolder, position: Int) {
        holder.binding.setVariable(BR.data,list[position])
        itemClickedListenerTv?.let { holder.binding.setVariable(BR.callBack,it) }
    }

}