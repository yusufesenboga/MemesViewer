package com.agobnese.memesviewer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agobnese.memesviewer.model.MemesContainer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_item_view.view.*


class MemesListAdapter(private val memesContainer: MemesContainer, val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MemesListAdapter.MemesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.single_item_view, parent, false)
        return MemesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemesViewHolder, position: Int) {
        holder.itemView.run {
            Log.d("ApplicationTag","before tagging the name for RV")
            memeName.text = memesContainer.data.memes[position].name
            Log.d("ApplicationTag","after tagging the name for RV")
            Picasso.get().load(memesContainer.data.memes[position].url).into(memeImage)
            Log.d("ApplicationTag","after tagging the image for RV")
        }
    }

    override fun getItemCount(): Int {
//        return memesContainer.data.memes.size
        return 10
    }

    inner class MemesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClick.invoke(adapterPosition)
            }
        }
    }
}