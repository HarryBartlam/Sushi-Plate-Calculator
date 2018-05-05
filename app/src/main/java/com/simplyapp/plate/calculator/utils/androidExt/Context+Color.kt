package com.simplyapp.plate.calculator.utils.androidExt

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView

inline fun Context.getCompatColor(@ColorRes colorId: Int): Int {
    return ResourcesCompat.getColor(resources, colorId, null)
}

inline fun RecyclerView.ViewHolder.getColor(@ColorRes colorId: Int): Int {
    return itemView.context.getCompatColor(colorId)
}