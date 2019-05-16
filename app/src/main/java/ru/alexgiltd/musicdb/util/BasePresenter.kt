package ru.alexgiltd.musicdb.util

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    private val disposables = CompositeDisposable()

    protected fun unsubscribeOnDestroy(subscription: Disposable) {
        disposables.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
