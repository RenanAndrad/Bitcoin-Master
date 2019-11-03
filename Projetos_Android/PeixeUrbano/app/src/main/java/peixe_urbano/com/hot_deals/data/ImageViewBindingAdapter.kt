package peixe_urbano.com.hot_deals.data

import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter


object ImageViewBindingAdapter {

    @BindingAdapter("url")
    fun loadImage(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(imageView).load(url).into(imageView)
        } else {
            imageView.setImageDrawable(null)
        }
    }
}