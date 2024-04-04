package com.eroglu.movieapp.util

import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.eroglu.movieapp.util.Constants.IMAGE_BASE_URL

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["loadImage"], requireAll = false)
    fun loadImage(view: ImageView, url: String?) {
        safeLet(url, view.context) { _url, context ->
            Glide.with(context)
                .load("${IMAGE_BASE_URL}${_url}")
                .placeholder(android.R.color.darker_gray)
                .apply(
                    RequestOptions().transform(
                        MultiTransformation(
                            CenterCrop(),
                            RoundedCorners(5)
                        )
                    )
                )
                .into(view)

        }
    }

    @JvmStatic
    @BindingAdapter(value = ["isBold"])
    fun setTextBold(view: TextView,isBold: Boolean){
        view.typeface = Typeface.DEFAULT_BOLD
    }

    @JvmStatic
    @BindingAdapter(value = ["isBackButton"])
    fun isBackButton(view: View,isBackButton: Boolean){
        if (isBackButton) {
            view.setOnClickListener{
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

}