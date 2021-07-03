package com.agobnese.memesviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agobnese.memesviewer.model.MemesContainer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_item_view.view.*


class MemesListAdapter(
    private val memesContainer: MemesContainer, val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MemesListAdapter.MemesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.single_item_view, parent, false)
        return MemesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemesViewHolder, position: Int) {
        holder.itemView.run {
            memeName.text = memesContainer.data.memes[position].name
            Picasso.get().load(memesContainer.data.memes[position].url).into(memeImage)
        }
    }

    override fun getItemCount(): Int {
        return memesContainer.data.memes.size
    }

    inner class MemesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClick.invoke(adapterPosition)
            }
        }
    }
}
