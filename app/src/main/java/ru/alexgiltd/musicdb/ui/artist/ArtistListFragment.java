package ru.alexgiltd.musicdb.ui.artist;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.alexgiltd.musicdb.App;
import ru.alexgiltd.musicdb.R;
import ru.alexgiltd.musicdb.model.ArtistModel;
import ru.alexgiltd.musicdb.presentation.artist.ArtistListPresenter;
import ru.alexgiltd.musicdb.presentation.artist.ArtistListView;
import ru.alexgiltd.musicdb.util.moxy.mvp.MvpAppCompatFragment;


public class ArtistListFragment extends MvpAppCompatFragment implements ArtistListView {

    public static final String TAG = "ArtistListFragment";

    private ContentLoadingProgressBar progressBar;
    private RecyclerView artistsRecycler;
    private ArtistListAdapter artistsAdapter = new ArtistListAdapter();

    @Inject
    Provider<ArtistListPresenter> presenterProvider;
    @InjectPresenter
    ArtistListPresenter presenter;

    @ProvidePresenter
    ArtistListPresenter providePresenter() {
        return presenterProvider.get();
    }

    public static ArtistListFragment newInstance() {
        return new ArtistListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getAppComponent()
                .inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        progressBar = v.findViewById(R.id.fragment_artists_progress_bar);
        artistsRecycler = v.findViewById(R.id.recycler_view_artists);

        artistsRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        artistsRecycler.setAdapter(artistsAdapter);
        artistsAdapter.setOnItemClickListener(artist ->
                presenter.onItemClicked(artist));
        artistsRecycler.setHasFixedSize(true);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(
                artistsRecycler,
                requireContext().getText(R.string.error_text),
                Snackbar.LENGTH_LONG
        ).show();
    }

    @Override
    public void onStartLoading() {
//        artistsRecycler.setVisibility(View.INVISIBLE);
//        progressBar.setVisibility(View.VISIBLE);
        progressBar.show();
    }

    @Override
    public void onFinishLoading() {
//        artistsRecycler.setVisibility(View.VISIBLE);
//        progressBar.setVisibility(View.GONE);
        progressBar.hide();
    }

    @Override
    public void showArtists(List<ArtistModel> list) {
        artistsAdapter.submitList(list);
    }

    @Override
    public void openArtistDetails(ArtistModel artistDetailsModel) {
        ArtistDetailsActivity.start(requireActivity(), artistDetailsModel.getName());

/*        MainActivity.openFragment(
                (AppCompatActivity)requireActivity(),
                ArtistDetailsFragment.newInstance(artistDetailsModel),
                TAG
        );*/
    }

}
