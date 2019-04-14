package ru.alexgiltd.musicdb.util;

import androidx.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends MvpView> extends MvpPresenter<V> {
    private CompositeDisposable disposables = new CompositeDisposable();

    protected void unsubscribeOnDestroy(@NonNull Disposable subscription) {
        disposables.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
