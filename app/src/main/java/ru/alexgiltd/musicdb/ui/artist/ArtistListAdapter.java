package ru.alexgiltd.musicdb.ui.artist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.alexgiltd.musicdb.R;
import ru.alexgiltd.musicdb.model.ArtistModel;
import ru.alexgiltd.musicdb.util.adapter.BaseViewHolder;
import ru.alexgiltd.musicdb.util.adapter.BaseRecyclerAdapter;

import static ru.alexgiltd.musicdb.util.Constants.LARGE_IMAGE;

public class ArtistListAdapter extends BaseRecyclerAdapter<ArtistModel, ArtistListAdapter.ViewHolder> {

    private static final DiffUtil.ItemCallback<ArtistModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ArtistModel>() {
                @Override
                public boolean areItemsTheSame(ArtistModel oldItem, ArtistModel newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @Override
                public boolean areContentsTheSame(ArtistModel oldItem, @NonNull ArtistModel newItem) {
                    return oldItem.equals(newItem);
                }
            };

    protected ArtistListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(v -> {
            if (getOnItemClickListener() == null) {
                return;
            }
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                getOnItemClickListener().onItemClick(getItem(adapterPosition));
            }
        });
        return viewHolder;
    }

    static class ViewHolder extends BaseViewHolder<ArtistModel> {
        private final TextView artisDetailsText;
        private final TextView artistNameText;
        private final TextView artistListenersCount;
        private final ImageView artistImage;

        ViewHolder(View itemView) {
            super(itemView);
            artistNameText = itemView.findViewById(R.id.artist_name);
            artistImage = itemView.findViewById(R.id.artist_image);
            artistListenersCount = itemView.findViewById(R.id.artist_listeners);
            artisDetailsText = itemView.findViewById(R.id.artist_details_text);
        }

        @Override
        public void bind(ArtistModel artist) {
            artistNameText.setText(artist.getName());

            artistListenersCount.setText(
                    itemView.getContext()
                            .getString(R.string.artist_listeners, artist.getListeners())
            );
            artisDetailsText.setText(artist.getSummary());

            artistImage.setContentDescription(
                    itemView.getContext()
                            .getString(R.string.artist_image_description, artist.getName())
            );
            Picasso.get()
                    .load(artist.getImages().get(LARGE_IMAGE))
                    .fit()
                    .centerCrop()
                    .into(artistImage);
        }
    }
}
