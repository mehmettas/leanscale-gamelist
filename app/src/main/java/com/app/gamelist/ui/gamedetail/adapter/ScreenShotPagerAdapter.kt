package com.app.gamelist.ui.gamedetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.app.gamelist.R
import com.app.gamelist.data.remote.model.gamelist.screenshoot.ScreenShoot
import com.app.gamelist.utils.kotlinextensions.load
import kotlinx.android.synthetic.main.layout_item_pager.view.*

class ScreenShotPagerAdapter (
    private var items: ArrayList<ScreenShoot> = arrayListOf(),
    private var context: Context
): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_item_pager, null)
        view.imgGame.load(items.get(position).image) // Set image
        container.addView(view)
        return view
    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getCount(): Int {
        return items.size
    }
}