package br.edu.ifsp.moviesmanager.controller

import android.os.AsyncTask
import androidx.room.Room
import br.edu.ifsp.moviesmanager.models.dao.MovieRoomDAO
import br.edu.ifsp.moviesmanager.models.dao.MovieRoomDAO.Constants.MOVIE_DATABASE_FILE
import br.edu.ifsp.moviesmanager.models.database.MovieRoomDAODatabase
import br.edu.ifsp.moviesmanager.models.entities.Movie
import br.edu.ifsp.moviesmanager.views.MainActivity

class MovieRoomController(private val mainActivity: MainActivity) {
    private val movieDAOImpl: MovieRoomDAO by lazy {
        Room.databaseBuilder(
            mainActivity,
            MovieRoomDAODatabase::class.java,
            MOVIE_DATABASE_FILE
        ).build().getMovieRoomDAO()
    }

    fun insertMovie(movie: Movie) {
        Thread {
            movieDAOImpl.createMovie(movie)
            getMovies()
        }.start()
    }

    fun getContact(id: Int) = movieDAOImpl.retrieveMovie(id)
    fun getMovies() {
        object : AsyncTask<Unit, Unit, MutableList<Movie>>() {
            override fun doInBackground(vararg params: Unit?): MutableList<Movie> {
                val returnList = mutableListOf<Movie>()
                returnList.addAll(movieDAOImpl.retrieveMovies())
                return returnList
            }

            override fun onPostExecute(result: MutableList<Movie>?) {
                super.onPostExecute(result)
                if (result != null) {
                    mainActivity.updateMovieList(result)
                }
            }
        }.execute()
    }

    fun editMovie(movie: Movie) {
        Thread {
            movieDAOImpl.updateMovie(movie)
            getMovies()
        }.start()
    }

    fun removeContact(movie: Movie) {
        Thread {
            movieDAOImpl.deleteMovie(movie)
            getMovies()
        }.start()
    }
}