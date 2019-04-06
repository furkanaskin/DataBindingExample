package com.faskn.databindingexample.Objects

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter{

    @JvmStatic
    @BindingAdapter("app:setDrawable")
    fun setDrawable(view : ImageView,drawable : Int){
        view.setImageResource(drawable)
    }

    @JvmStatic
    @BindingAdapter("app:setTextColor")
    fun setDrawable(view : TextView,color : Int){
        view.setTextColor(color)
    }
}