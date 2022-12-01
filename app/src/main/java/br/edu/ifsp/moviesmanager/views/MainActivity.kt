package br.edu.ifsp.moviesmanager.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.moviesmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
    }
}