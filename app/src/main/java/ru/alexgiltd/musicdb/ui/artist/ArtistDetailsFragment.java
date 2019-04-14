package ru.alexgiltd.musicdb.ui.artist;


import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.alexgiltd.musicdb.App;
import ru.alexgiltd.musicdb.R;
import ru.alexgiltd.musicdb.model.SimpleArtistModel;
import ru.alexgiltd.musicdb.presentation.artist.ArtistDetailsPresenter;
import ru.alexgiltd.musicdb.presentation.artist.ArtistDetailsView;
import ru.alexgiltd.musicdb.util.moxy.mvp.MvpAppCompatFragment;

public class ArtistDetailsFragment extends MvpAppCompatFragment implements ArtistDetailsView {

    public static final String TAG = "ArtistDetailsFragment";
    public static final String ARTIST_DETAILS_ARG_STRING = "ARTIST_DETAILS_ARG_STRING";

    @Inject
    Provider<ArtistDetailsPresenter> presenterProvider;
    @InjectPresenter
    ArtistDetailsPresenter presenter;

    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView artistImageView;
    private TextView artistBiographyText;
    private FloatingActionButton addToFavoritesBtn;
    private RecyclerView similarArtistsRecycler;
    private AppBarLayout appBarLayout;

    private SimilarArtistListAdapter similarArtistsAdapter = new SimilarArtistListAdapter();
    private ContentLoadingProgressBar progressBar;
    private View content;

    public static ArtistDetailsFragment newInstance(String artistName) {
        Bundle bundle = new Bundle();
        bundle.putString(ARTIST_DETAILS_ARG_STRING, artistName);

        ArtistDetailsFragment fragment = new ArtistDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @ProvidePresenter
    ArtistDetailsPresenter providePresenter() {
        ArtistDetailsPresenter presenter = presenterProvider.get();
        presenter.setArtistDetailsModel(getArguments().getString(ARTIST_DETAILS_ARG_STRING));
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getAppComponent()
                .inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.fragment_artist_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {

        appBarLayout = v.findViewById(R.id.app_bar);
        collapsingToolbar = v.findViewById(R.id.collapsing_toolbar);
        artistImageView = v.findViewById(R.id.toolbar_image_main);

        progressBar = v.findViewById(R.id.progressbar_artist_details);
        content = v.findViewById(R.id.content);
        artistBiographyText = v.findViewById(R.id.text_artist_details_biography);
        addToFavoritesBtn = v.findViewById(R.id.btn_add_to_favorites);

        similarArtistsRecycler = v.findViewById(R.id.recycler_similar_artist_list);
        similarArtistsRecycler.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        );
        similarArtistsRecycler.setAdapter(similarArtistsAdapter);
        similarArtistsAdapter.setOnItemClickListener(artistModel ->
                presenter.onSimilarArtistItemClicked(artistModel));

        addToFavoritesBtn.setOnClickListener(view -> presenter.onFavoriteBtnClicked());
    }

    @Override
    public void showArtistName(String artistName) {
        collapsingToolbar.setTitle(artistName);
    }

    @Override
    public void showArtistImage(String artistImage) {
        Picasso.get()
                .load(artistImage)
                .fit()
                .centerCrop(Gravity.TOP)
                .into(artistImageView, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        appBarLayout.setExpanded(true);
                    }
                });
    }

    @Override
    public void showArtistDescription(String artistDesc) {
        artistBiographyText.setText(artistDesc);
    }

    @Override
    public void showSimilarArtists(List<SimpleArtistModel> artistList) {
        similarArtistsAdapter.submitList(artistList);
    }

    @Override
    public void showFavoriteArtistNotification(String artistName) {
        Snackbar.make(
                requireView(),
                requireContext().getString(R.string.favorite_button_text, artistName),
                Snackbar.LENGTH_LONG
        ).show();
    }

    @Override
    public void showError(String message) {
        Snackbar.make(
                requireView(),
                collapsingToolbar.getContext().getText(R.string.error_text),
                Snackbar.LENGTH_LONG
        ).show();
    }

    @Override
    public void onStartLoading() {
        content.setVisibility(View.GONE);
        progressBar.show();
    }

    @Override
    public void onFinishLoading() {
        content.setVisibility(View.VISIBLE);
        progressBar.hide();
    }

    @Override
    public void openArtistDetails(String artistName) {
        ArtistDetailsActivity.start(requireActivity(), artistName);
    }
}
