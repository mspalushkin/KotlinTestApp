/*
 * 27.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.ui

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.palushkin.kotlintestapp.R
import com.palushkin.kotlintestapp.network.User
import com.palushkin.kotlintestapp.ui.home.UserApiStatus
import com.palushkin.kotlintestapp.ui.home.UserListAdapter

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitList(data)
}


@BindingAdapter("userApiStatusImageView")
fun bindStatusImage(statusImageView: ImageView, status: UserApiStatus?) {
    when (status) {
        UserApiStatus.LOADING -> {
            statusImageView.visibility = View.GONE
        }
        UserApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        UserApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("userApiStatusProgressBar")
fun bindStatusProgress(progressBarView: ProgressBar, status: UserApiStatus?) {
    when (status) {
        UserApiStatus.LOADING -> {
            progressBarView.visibility = View.VISIBLE
        }
        UserApiStatus.ERROR -> {
            progressBarView.visibility = View.GONE
        }
        UserApiStatus.DONE -> {
            progressBarView.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                        RequestOptions()
                                //.placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                )
                .into(imgView)
    }
}