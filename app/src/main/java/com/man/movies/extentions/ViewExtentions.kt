package com.man.movies.extentions

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.man.data.BuildConfig
import com.man.movies.R

fun ImageView.fromUrl(url : String){
    Glide.with(context)
        .load(BuildConfig.IMG600_URL + url)
        .placeholder(R.drawable.img_cinema)
        .apply(requestOptions)
        .into(this)
}

val requestOptions = RequestOptions()
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .signature(ObjectKey("movie")).override(100, 100)

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun hideKeyboard(activity: Activity) {
    try {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

