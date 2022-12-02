package br.edu.ifsp.moviesmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.moviesmanager.R
import br.edu.ifsp.moviesmanager.adapters.MovieAdapter
import br.edu.ifsp.moviesmanager.databinding.ActivityMainBinding
import br.edu.ifsp.moviesmanager.models.Constants.EXTRA_MOVIE
import br.edu.ifsp.moviesmanager.models.Constants.VIEW_MOVIE
import br.edu.ifsp.moviesmanager.models.entities.Movie

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val movieList: MutableList<Movie> = mutableListOf()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        movieAdapter = MovieAdapter(this, movieList)
        activityMainBinding.moviesListView.adapter = movieAdapter

        movieActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val movie = result.data?.getParcelableExtra<Movie>(EXTRA_MOVIE)

                movie?.let { _movie ->
                    val position = movieList.indexOfFirst { it.id == _movie.id }
                    if (position != -1) {
                        movieList[position] = _movie
                    } else {
                        movieList.add(_movie)
                    }
                    movieAdapter.notifyDataSetChanged()
                }
            }
        }
        registerForContextMenu(activityMainBinding.moviesListView)

        activityMainBinding.moviesListView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val movie = movieList[position]
                val movieIntent = Intent(this@MainActivity, MovieActivity::class.java)
                movieIntent.putExtra(EXTRA_MOVIE, movie)
                movieIntent.putExtra(VIEW_MOVIE, true)
                startActivity(movieIntent)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addMovieMi -> {
                movieActivityResultLauncher.launch(Intent(this, MovieActivity::class.java))
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_main_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        return when (item.itemId) {
            R.id.editMovieMi -> {
                val bill = movieList[position]
                val movieIntent = Intent(this, MovieActivity::class.java)
                movieIntent.putExtra(EXTRA_MOVIE, bill)
                movieIntent.putExtra(VIEW_MOVIE, false)
                movieActivityResultLauncher.launch(movieIntent)
                true
            }
            R.id.removeMovieMi -> {
                movieList.removeAt(position)
                movieAdapter.notifyDataSetChanged()
                true
            }
            else -> {
                false
            }
        }
    }
}