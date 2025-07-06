package com.example.dots.utilities

import android.graphics.drawable.ColorDrawable
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

fun createShimmerDrawable(): ShimmerDrawable {
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1000)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(1.0f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .build()

    return ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
}
