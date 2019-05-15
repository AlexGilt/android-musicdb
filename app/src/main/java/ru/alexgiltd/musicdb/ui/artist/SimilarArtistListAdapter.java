package ru.alexgiltd.musicdb.ui.artist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.alexgiltd.musicdb.R;
import ru.alexgiltd.musicdb.model.SimpleArtistModel;
import ru.alexgiltd.musicdb.util.adapter.BaseRecyclerAdapter;
import ru.alexgiltd.musicdb.util.adapter.BaseViewHolder;

import static ru.alexgiltd.musicdb.util.Constants.LARGE_IMAGE;

public class SimilarArtistListAdapter extends
        BaseRecyclerAdapter<SimpleArtistModel, SimilarArtistListAdapter.ViewHolder> {

    private static final DiffUtil.ItemCallback<SimpleArtistModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<SimpleArtistModel>() {
        @Override
        public boolean areItemsTheSame(SimpleArtistModel oldItem, SimpleArtistModel newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(SimpleArtistModel oldItem, @NonNull SimpleArtistModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected SimilarArtistListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_similar_artist, parent, false);
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

    static class ViewHolder extends BaseViewHolder<SimpleArtistModel> {
        private final TextView artistNameText;
        private final ImageView artistImage;

        ViewHolder(View itemView) {
            super(itemView);
            artistNameText = itemView.findViewById(R.id.artist_name);
            artistImage = itemView.findViewById(R.id.artist_image);
        }

        @Override
        public void bind(SimpleArtistModel artist) {
            artistNameText.setText(artist.getName());

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
