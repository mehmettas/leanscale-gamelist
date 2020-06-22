package com.app.gamelist.ui.main.adapter

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.gamelist.R
import com.app.gamelist.data.remote.model.gamelist.GameList
import com.app.gamelist.utils.kotlinextensions.configurePlatformFigures
import com.app.gamelist.utils.kotlinextensions.configureRatingFigures
import com.app.gamelist.utils.kotlinextensions.inflate
import com.app.gamelist.utils.kotlinextensions.load
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import kotlinx.android.synthetic.main.layout_item_game.view.*

class GameListAdapter  (
    private var items: ArrayList<GameList> = arrayListOf(),
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

    fun addData(list: ArrayList<GameList>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): GameList = items[position]

    inner class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            gameItem: GameList,
            listener: CustomGameListListener,
            position: Int
        ) = with(itemView) {

            // Configure parent recyclerView
            itemView.imgGame.load(gameItem.backgroundImage)
            itemView.txtGameName.text = gameItem.gameName
            itemView.txtReleaseDate.text = gameItem.releasedDate
            itemView.txtCount.text = gameItem.addedCount.toString()
            itemView.configurePlatformFigures(gameItem)
            itemView.icRecommendationLeveling.configureRatingFigures(gameItem)
            itemView.setOnClickListener {
                listener.onGameItemSelected(gameItem)
            }

            // Configure child recyclerView
            val chipsLayoutManager = ChipsLayoutManager.newBuilder(context)
                .setScrollingEnabled(true)
                .setGravityResolver { Gravity.NO_GRAVITY }
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                .build()

            itemView.recyclerviewGenres.apply {
                setHasFixedSize(true)
                layoutManager = chipsLayoutManager
                adapter = ChipAdapter(gameItem.genres)
                addItemDecoration(
                    SpacingItemDecoration(
                        resources.getDimensionPixelOffset(R.dimen.chip_margin),
                        resources.getDimensionPixelOffset(R.dimen.chip_margin)
                    )
                )
            }

        }
    }

    interface CustomGameListListener {
        fun onGameItemSelected(gameItem: GameList)
    }

}