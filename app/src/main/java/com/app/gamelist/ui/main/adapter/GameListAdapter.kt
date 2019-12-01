package com.app.gamelist.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.gamelist.R
import com.app.gamelist.utils.kotlinextensions.inflate

class GameListAdapter  (
    private var items: ArrayList<String> = arrayListOf(),
    private val listener: GameListAdapter.CustomGameListListener
) : RecyclerView.Adapter<GameListAdapter.GameListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        return GameListViewHolder(parent.inflate(R.layout.layout_item_game))
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.bind(getItem(position), listener, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(list: ArrayList<String>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): String = items[position]

    inner class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            gameItem: String,
            listener: CustomGameListListener,
            position: Int
        ) = with(itemView) {

            itemView.setOnClickListener {
                listener.onGameItemSelected(itemView)
            }

        }
    }

    interface CustomGameListListener {
        fun onGameItemSelected(itemSession: View)
    }

}