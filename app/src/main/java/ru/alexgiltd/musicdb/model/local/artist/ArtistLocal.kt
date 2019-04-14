package ru.alexgiltd.musicdb.model.local.artist


import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "artist",
        indices = [
            Index(value = ["name"], unique = true)
        ]
)
data class ArtistLocal(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "mbid") val mbid: String? = null,
        @ColumnInfo(name = "url") val url: String
) {
    @Ignore
    var images: List<Image>? = null
}

@Entity(tableName = "artist_image",
        foreignKeys = [
            ForeignKey(
                    entity = ArtistLocal::class,
                    parentColumns = ["id"],
                    childColumns = ["artist_id"],
                    onDelete = CASCADE
            )
        ]
)
data class Image(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "artist_id") val artistId: Long,
        @ColumnInfo(name = "size") val size: String,
        @ColumnInfo(name = "url") val url: String
)

data class ArtistWithImage(
//        @Embedded val artist: ArtistLocal,
//        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "mbid") val mbid: String = "",
        @ColumnInfo(name = "url") val url: String,
        @Relation(parentColumn = "id", entityColumn = "artist_id") val images: List<Image>
        // TODO: consider implementing a type converter for List<Image> to Map<Sting, String>
)