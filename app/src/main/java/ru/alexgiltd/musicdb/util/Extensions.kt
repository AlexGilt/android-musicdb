package ru.alexgiltd.musicdb.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.alexgiltd.musicdb.R

fun AppCompatActivity.openFragmentWithBackStack(fragment: Fragment, fragmentTag: String) {
    val fragmentManager = supportFragmentManager
    val createdFragment = fragmentManager.findFragmentByTag(fragmentTag) ?: fragment

    fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, createdFragment, fragmentTag)
            .addToBackStack(null)
            .commitAllowingStateLoss()
}

fun AppCompatActivity.openFragmentWithoutBackStack(fragment: Fragment, fragmentTag: String) {
    val fragmentManager = supportFragmentManager
    val createdFragment = fragmentManager.findFragmentByTag(fragmentTag) ?: fragment

    fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, createdFragment, fragmentTag)
            .commit()
}
