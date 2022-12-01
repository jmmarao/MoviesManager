package br.edu.ifsp.moviesmanager.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import br.edu.ifsp.moviesmanager.R
import br.edu.ifsp.moviesmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}