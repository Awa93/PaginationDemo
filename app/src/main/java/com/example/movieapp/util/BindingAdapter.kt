package com.example.movieapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun loadImage(view:ImageView,url:String){
    Glide.with(view).load(url).into(view)
}