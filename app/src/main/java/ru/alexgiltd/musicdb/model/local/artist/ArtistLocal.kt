package ru.alexgiltd.musicdb.model.local.artist


import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
        tableName = "artist",
        indices = [ Index(value = ["name"], unique = true) ]
)
data class ArtistLocal(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "mbid") val mbid: String? = null,
        @ColumnInfo(name = "url") val url: String
) {
    @Ignore
    var images: List<ImageLocal>? = null

    override fun toString(): String {
        return "ArtistLocal(id=$id, name='$name', mbid=$mbid, url='$url', images=$images)"
    }
}

@Entity(
        tableName = "artist_info",
        foreignKeys = [
            ForeignKey(
                entity = ArtistLocal::class,
                parentColumns = ["id"],
                childColumns = ["artist_id"]
            )
        ],
        indices = [ Index(value = ["similar_artist_id"], unique = true) ]
)
data class ArtistInfoLocal(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "artist_id") var artistId: Long = 0,
        @ColumnInfo(name = "similar_artist_id") var similarArtistId: Long = 0,
        @ColumnInfo(name = "isStreamable") val isStreamable: Boolean,
        @ColumnInfo(name = "isOnTour") val isOnTour: Boolean,
        @ColumnInfo(name = "listeners") val listeners: Long,
        @ColumnInfo(name = "playcount") val playcount: Long,
////        val tags: List<TagModel>,
        @ColumnInfo(name = "published") val published: String,
        @ColumnInfo(name = "summary") val summary: String,
        @ColumnInfo(name = "content") val content: String
) {
    @Ignore
    var similarArtists: List<ArtistLocal>? = null

    override fun toString(): String =
            "ArtistInfoLocal(id=$id, artistId=$artistId, similarArtistId=$similarArtistId," +
                    " isStreamable=$isStreamable, isOnTour=$isOnTour, listeners=$listeners," +
                    " playcount=$playcount, published='$published', summary='$summary'," +
                    " content='$content', similarArtists=$similarArtists)"
}

@Entity(
        tableName = "artist_image",
        foreignKeys = [
            ForeignKey(
                    entity = ArtistLocal::class,
                    parentColumns = ["id"],
                    childColumns = ["artist_id"],
                    onDelete = CASCADE
            )
        ]
)
data class ImageLocal(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "artist_id") var artistId: Long = 0,
        @ColumnInfo(name = "size") val size: String,
        @ColumnInfo(name = "url") val url: String
)

@Entity(
        tableName = "artist_to_similar_artist",
        foreignKeys = [
            ForeignKey(
                    entity = ArtistInfoLocal::class,
                    parentColumns = ["similar_artist_id"],
                    childColumns = ["artist_id1"]
            ),
            ForeignKey(
                    entity = ArtistLocal::class,
                    parentColumns = ["id"],
                    childColumns = ["artist_id2"]
            )
        ]
)
data class ArtistInfoToArtistJoin(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "artist_id1") val firstId: Long,
        @ColumnInfo(name = "artist_id2") val secondId: Long
)

data class ArtistWithImagesLocal(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "mbid") val mbid: String = "",
        @ColumnInfo(name = "url") val url: String,
        @Relation(parentColumn = "id", entityColumn = "artist_id") val images: List<ImageLocal>
)