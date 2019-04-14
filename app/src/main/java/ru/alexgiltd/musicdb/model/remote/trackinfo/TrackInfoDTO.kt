package ru.alexgiltd.musicdb.model.remote.trackinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrackInfoDTO(
        @Expose @SerializedName("name") val name: String,
        @Expose @SerializedName("mbid") val mbid: String,
        @Expose @SerializedName("url") val url: String,
        @Expose @SerializedName("duration") val duration: String,
        @Expose @SerializedName("streamable") val streamable: StreamableDTO,
        @Expose @SerializedName("listeners") val listeners: String,
        @Expose @SerializedName("playcount") val playcount: String,
        @Expose @SerializedName("artist") val artist: ArtistDTO,
        @Expose @SerializedName("album") val album: AlbumDTO,
        @Expose @SerializedName("toptags") val toptags: ToptagsDTO,
        @Expose @SerializedName("wiki") val wiki: WikiDTO
)