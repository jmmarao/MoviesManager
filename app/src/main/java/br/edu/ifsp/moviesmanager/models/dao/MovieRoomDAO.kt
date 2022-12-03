package br.edu.ifsp.moviesmanager.models.dao

import androidx.room.*
import br.edu.ifsp.moviesmanager.models.entities.Movie

@Dao
interface MovieRoomDAO {
    companion object Constants {
        const val MOVIE_DATABASE_FILE = "movies_room"
        const val MOVIE_TABLE = "movie"
        const val ID_COLUMN = "id"
        const val NAME_COLUMN = "name"
        const val SCORE_COLUMN = "score"
    }

    @Insert
    fun createMovie(movie: Movie)

    @Query("SELECT * FROM $MOVIE_TABLE WHERE $ID_COLUMN = :id")
    fun retrieveMovie(id: Int): Movie?

    @Query("SELECT * FROM $MOVIE_TABLE")
    fun retrieveMovies(): MutableList<Movie>

    @Query("SELECT * FROM $MOVIE_TABLE ORDER BY $NAME_COLUMN")
    fun retrieveMoviesSortedByName(): MutableList<Movie>

    @Query("SELECT * FROM $MOVIE_TABLE ORDER BY $SCORE_COLUMN")
    fun retrieveMoviesSortedByScore(): MutableList<Movie>

    @Update
    fun updateMovie(movie: Movie): Int

    @Delete
    fun deleteMovie(movie: Movie): Int
}