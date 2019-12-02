package com.app.gamelist.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.gamelist.R
import com.app.gamelist.data.remote.model.gamelist.GameList
import com.app.gamelist.data.remote.model.gamelist.genre.Genre
import com.app.gamelist.utils.kotlinextensions.inflate
import kotlinx.android.synthetic.main.layout_item_genre.view.*

class ChipAdapter (
    private var items: ArrayList<Genre> = arrayListOf())
    : RecyclerView.Adapter<ChipAdapter.ChipViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        return ChipViewHolder(parent.inflate(R.layout.layout_item_genre))
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(list: ArrayList<Genre>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): Genre = items[position]

    inner class ChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            chipItem: Genre,
            selectedChip: Int
        ) = with(itemView) {
            itemView.txtGenreName.text = chipItem.genreName
        }
    }

}