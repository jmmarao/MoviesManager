package br.edu.ifsp.moviesmanager.adapters

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.moviesmanager.R
import br.edu.ifsp.moviesmanager.models.entities.Movie

class MovieAdapter(
    context: Context,
    private val movieList: MutableList<Movie>
) : ArrayAdapter<Movie>(context, R.layout.tile_movie, movieList) {
    private data class TileMovieHolder(val movieTextView: TextView, val scoreTextView: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val movie = movieList[position]
        var movieTileView = convertView

        if (movieTileView == null) {
            movieTileView = (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.tile_movie, parent, false)

            val tileMovieHolder = TileMovieHolder(
                movieTileView.findViewById(R.id.movieTextView),
                movieTileView.findViewById(R.id.scoreTextView)
            )
            movieTileView.tag = tileMovieHolder
        }
        with(movieTileView?.tag as TileMovieHolder) {
            movieTextView.text = movie.name
            scoreTextView.text = movie.score.toString()
        }
        return movieTileView
    }
}