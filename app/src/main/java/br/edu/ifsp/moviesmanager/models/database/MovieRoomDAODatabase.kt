package br.edu.ifsp.moviesmanager.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifsp.moviesmanager.models.dao.MovieRoomDAO
import br.edu.ifsp.moviesmanager.models.entities.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieRoomDAODatabase : RoomDatabase() {
    abstract fun getMovieRoomDAO(): MovieRoomDAO
}