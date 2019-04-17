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
import ru.alexgiltd.musicdb.model.ArtistModel
import ru.alexgiltd.musicdb.util.Constants.LARGE_IMAGE

class ArtistListAdapter : ListAdapter<ArtistModel, ArtistListAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClickListener: ((ArtistModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_artist, parent, false)
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
        private val artistDetailsText: TextView = itemView.findViewById(R.id.artist_details_text)
        private val artistNameText: TextView = itemView.findViewById(R.id.artist_name)
        private val artistListenersCount: TextView = itemView.findViewById(R.id.artist_listeners)
        private val artistImage: ImageView = itemView.findViewById(R.id.artist_image)

        fun bind(artist: ArtistModel) {
            artistNameText.text = artist.name

            artistListenersCount.text = itemView.context.getString(
                    R.string.artist_listeners,
                    artist.listeners
            )
            artistDetailsText.text = artist.summary

            artistImage.contentDescription = itemView.context.getString(
                    R.string.artist_image_description,
                    artist.name
            )
            Picasso.get()
                    .load(artist.images?.get(LARGE_IMAGE))
                    .fit()
                    .centerCrop()
                    .into(artistImage)
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArtistModel>() {
            override fun areItemsTheSame(oldItem: ArtistModel, newItem: ArtistModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ArtistModel, newItem: ArtistModel): Boolean {
                return oldItem == newItem
            }
        }

    }
}
