package ru.alexgiltd.musicdb.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.alexgiltd.musicdb.ui.home.HomeFragment;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Override
    protected void onFirstViewAttach() {
        showTab(HomeFragment.TAG);
    }

    public void onTabItemClicked(String tabName) {
        showTab(tabName);
    }

    private void showTab(String tabName) {
        getViewState().showTabTitle(tabName);
        getViewState().displayTab(tabName);
    }
}
