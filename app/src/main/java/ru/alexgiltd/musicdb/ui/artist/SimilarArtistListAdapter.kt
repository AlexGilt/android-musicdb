package ru.alexgiltd.musicdb.ui.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.alexgiltd.musicdb.R
import ru.alexgiltd.musicdb.model.SimpleArtistModel
import ru.alexgiltd.musicdb.util.Constants.LARGE_IMAGE

class SimilarArtistListAdapter : ListAdapter<SimpleArtistModel, SimilarArtistListAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClickListener: ((SimpleArtistModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_similar_artist, parent, false)
        val viewHolder = ViewHolder(view)

        view.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClickListener?.invoke(getItem(adapterPosition))
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistNameText: TextView = itemView.findViewById(R.id.artist_name)
        private val artistImage: ImageView = itemView.findViewById(R.id.artist_image)

        fun bind(artist: SimpleArtistModel) {
            artistNameText.text = artist.name

            artistImage.contentDescription = itemView.context
                    .getString(R.string.artist_image_description, artist.name)

            Picasso.get()
                    .load(artist.images?.get(LARGE_IMAGE))
                    .fit()
                    .centerCrop()
                    .into(artistImage)
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SimpleArtistModel>() {
            override fun areItemsTheSame(oldItem: SimpleArtistModel, newItem: SimpleArtistModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: SimpleArtistModel, newItem: SimpleArtistModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
