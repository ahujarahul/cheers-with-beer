package com.rahulahuja.cheerswithbeer.utils

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.rahulahuja.cheerswithbeer.R

/**
 * Created by rahulahuja on 16/04/20.
 */
fun View.applyBackgroundColor(color: Int) {
    val backgroundColor = ContextCompat.getColor(context, color)
    (this.background as GradientDrawable).setColor(backgroundColor)
}

fun AppCompatImageView.loadImage(imageUri: String) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context)
        .load(imageUri)
        .placeholder(circularProgressDrawable)
        .error(R.drawable.ic_broken_image_black_24dp)
        .override(200, 300)
        .into(this)

    circularProgressDrawable.stop()
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)
}

fun showError(view: View, errorMessage: String) {
    Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show()
}

