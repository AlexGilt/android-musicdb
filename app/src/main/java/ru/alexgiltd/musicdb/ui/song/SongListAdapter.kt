package ru.alexgiltd.musicdb.ui.song

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
import ru.alexgiltd.musicdb.model.TrackModel

class SongListAdapter : ListAdapter<TrackModel, SongListAdapter.SongsViewHolder>(ITEM_CALLBACK) {

    var onItemClickListener: ((TrackModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        val viewHolder = SongsViewHolder(view)

        view.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClickListener?.invoke(getItem(adapterPosition))
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SongsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val songNameText: TextView = itemView.findViewById(R.id.song_name)
        private val artistNameText: TextView = itemView.findViewById(R.id.artist_name)
        private val songImage: ImageView = itemView.findViewById(R.id.song_image)

        fun bind(track: TrackModel) {
            songNameText.text = track.name
            artistNameText.text = track.trackOwner.name

            Picasso.get()
                    .load(track.image)
                    .fit()
                    .centerCrop()
                    .into(songImage)
        }
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<TrackModel>() {
            override fun areItemsTheSame(oldItem: TrackModel, newItem: TrackModel): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: TrackModel, newItem: TrackModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
