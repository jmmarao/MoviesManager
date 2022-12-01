package br.edu.ifsp.moviesmanager.models.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movie(
    val id: Int,
    var name: String,
    var releaseYear: Int,
    var producer: String,
    var duration: Int,
    var watched: Boolean,
    var score: Int,
    var genre: String
) : Parcelable