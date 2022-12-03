package br.edu.ifsp.moviesmanager.models.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    indices = [Index(
        value = ["name"],
        unique = true
    )]
)
class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @NonNull
    var releaseYear: Int,

    @NonNull
    var producer: String,

    @NonNull
    var duration: Int,

    @NonNull
    var genre: String,

    @NonNull
    var watched: Boolean,

    var score: Int
) : Parcelable