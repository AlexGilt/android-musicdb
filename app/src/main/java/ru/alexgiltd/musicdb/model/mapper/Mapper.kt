package ru.alexgiltd.musicdb.model.mapper

import java.util.ArrayList

abstract class Mapper<T1, Result> {

    abstract fun map(value: T1): Result

    abstract fun reverseMap(value: Result): T1

    fun map(values: List<T1>): List<Result> {
        val returnValues = ArrayList<Result>(values.size)
        for (value in values) {
            returnValues.add(map(value))
        }
        return returnValues
    }

    fun reverseMap(values: List<Result>): List<T1> {
        val returnValues = ArrayList<T1>(values.size)
        for (value in values) {
            returnValues.add(reverseMap(value))
        }
        return returnValues
    }
}