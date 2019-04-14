package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.alexgiltd.musicdb.model.remote.BaseResponse


data class TrackInfoResponse(
        @Expose @SerializedName("track") val trackInfoDTO: TrackInfoDTO
) : BaseResponse()