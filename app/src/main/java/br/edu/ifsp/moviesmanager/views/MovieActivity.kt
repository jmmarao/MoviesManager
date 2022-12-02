package br.edu.ifsp.moviesmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.ifsp.moviesmanager.databinding.ActivityMovieBinding
import br.edu.ifsp.moviesmanager.models.Constants.EXTRA_MOVIE
import br.edu.ifsp.moviesmanager.models.Constants.VIEW_MOVIE
import br.edu.ifsp.moviesmanager.models.entities.Movie
import kotlin.random.Random

class MovieActivity : AppCompatActivity() {
    private val activityMovieBinding: ActivityMovieBinding by lazy {
        ActivityMovieBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMovieBinding.root)
        val receivedMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        receivedMovie?.let { _receivedMovie ->
            with(activityMovieBinding) {
                with(_receivedMovie) {
                    movieEditText.setText(name)
                    releaseYearEditText.setText(releaseYear.toString())
                    producerEditText.setText(producer)
                    durationEditText.setText(duration.toString())
                    genreSpinner.selectedItem.toString()
                    radioButtonNo.isChecked.toString()
                    scoreEditText.setText(score.toString())
                }
            }
        }

        val viewMovie = intent.getBooleanExtra(VIEW_MOVIE, false)
        if (viewMovie) {
            activityMovieBinding.movieEditText.isEnabled = false
            activityMovieBinding.releaseYearEditText.isEnabled = false
            activityMovieBinding.producerEditText.isEnabled = false
            activityMovieBinding.durationEditText.isEnabled = false
            activityMovieBinding.genreSpinner.isEnabled = false
            activityMovieBinding.radioButtonYes.isEnabled = false
            activityMovieBinding.radioButtonNo.isEnabled = false
            activityMovieBinding.scoreEditText.isEnabled = false
            activityMovieBinding.saveButton.visibility = View.GONE
        }

        activityMovieBinding.saveButton.setOnClickListener {
            val movie = Movie(
                id = receivedMovie?.id ?: Random(System.currentTimeMillis()).nextInt(),
                name = activityMovieBinding.movieEditText.text.toString(),
                releaseYear = activityMovieBinding.releaseYearEditText.text.toString().toInt(),
                producer = activityMovieBinding.producerEditText.text.toString(),
                duration = activityMovieBinding.durationEditText.text.toString().toInt(),
                genre = activityMovieBinding.genreSpinner.selectedItem.toString(),
                watched = activityMovieBinding.radioButtonNo.isChecked,
                score = activityMovieBinding.scoreEditText.text.toString().toInt()
            )

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_MOVIE, movie)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}