package com.app.gamelist.utils.kotlinextensions

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.TintAwareDrawable
import androidx.fragment.app.FragmentActivity
import com.app.gamelist.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

fun ImageView.tintSrc(@ColorRes colorRes: Int) {
    val drawable = DrawableCompat.wrap(drawable)
    DrawableCompat.setTint(drawable, ContextCompat.getColor(context, colorRes))
    setImageDrawable(drawable)
    if (drawable is TintAwareDrawable) invalidate() // Because in this case setImageDrawable will not call invalidate()
}


fun ImageView.loadFromUrl(url: String?) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)!!

fun ImageView.loadUrlAndPostponeEnterTransition(url: String, activity: FragmentActivity) {
    val target: Target<Drawable> = ImageViewBaseTarget(this, activity)
    Glide.with(context.applicationContext).load(url).placeholder(R.drawable.img_sample_game).into(target)
}

private class ImageViewBaseTarget(var imageView: ImageView?, var activity: FragmentActivity?) : BaseTarget<Drawable>() {
    override fun removeCallback(cb: SizeReadyCallback) {
        imageView = null
        activity = null
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        imageView?.setImageDrawable(resource)
        activity?.supportStartPostponedEnterTransition()
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
        activity?.supportStartPostponedEnterTransition()
    }

    override fun getSize(cb: SizeReadyCallback) = cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)
}


fun ImageView.load(imageUrl: String?) {
    Glide.with(context).load(imageUrl).placeholder(R.drawable.img_sample_game).into(this)
}

fun ImageView.load(imageUrl: String?, @DrawableRes placeHolderId: Int) {
    Glide.with(context).load(imageUrl).placeholder(placeHolderId).into(this)
}

fun ImageView.load(@DrawableRes resourceId: Int) {
    Glide.with(context).load(resourceId).placeholder(R.drawable.img_sample_game)
        .into(this)
}

fun ImageView.load(@DrawableRes resourceId: Int, cornersRadius: Int) {
    Glide.with(context).load(resourceId).transform(RoundedCorners(cornersRadius))
        .placeholder(R.drawable.img_sample_game)
        .into(this)
}

fun ImageView.loadCorner(@DrawableRes resourceId: Int, cornersRadius: Int) {
    Glide.with(context).load(resourceId).transform(RoundedCorners(cornersRadius))
        .placeholder(R.drawable.img_sample_game)
        .into(this)
}

fun ImageView.loadCornerFromUrl(url: String, cornersRadius: Int) {
    Glide.with(context).load(url).transform(RoundedCorners(cornersRadius)).placeholder(R.drawable.img_sample_game)
        .into(this)
}

fun ImageView.loadCircle(path: String) {
    Glide.with(context)
        .load(path)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.img_sample_game)
                .circleCrop()
        )
        .into(this)
}

/**
 * Loads image with Glide into the [ImageView].
 *
 * @param url url to load
 * @param previousUrl url that already loaded in this target. Needed to prevent white flickering.
 * @param round if set, the image will be round.
 * @param cornersRadius the corner radius to set. Only used if [round] is `false`(by default).
 * @param crop if set to `true` then [CenterCrop] will be used. Default is `false` so [FitCenter] is used.
 */
@SuppressLint("CheckResult")
fun ImageView.load(
    url: String,
    previousUrl: String? = null,
    round: Boolean = false,
    cornersRadius: Int = 0,
    crop: Boolean = false
) {

    val requestOptions = when {
        round -> RequestOptions.circleCropTransform()

        cornersRadius > 0 -> {
            RequestOptions().transforms(
                if (crop) CenterCrop() else FitCenter(),
                RoundedCorners(cornersRadius)
            )
        }

        else -> null
    }

    Glide
        .with(context)
        .load(url)
        .let {
            // Apply request options
            if (requestOptions != null) {
                it.apply(requestOptions)
            } else {
                it
            }
        }
        .let {
            // Workaround for the white flickering.
            // See https://github.com/bumptech/glide/issues/527
            // Thumbnail changes must be the same to catch the memory cache.
            if (previousUrl != null) {
                it.thumbnail(
                    Glide
                        .with(context)
                        .load(previousUrl)
                        .let {
                            // Apply request options
                            if (requestOptions != null) {
                                it.apply(requestOptions)
                            } else {
                                it
                            }
                        }
                )
            } else {
                it
            }
        }
        .into(this)
}