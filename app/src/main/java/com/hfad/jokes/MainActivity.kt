package com.hfad.jokes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hfad.jokes.databinding.ActivityMainBinding
import com.hfad.jokes.ui.JokesFragment
import com.hfad.jokes.ui.WebFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val jokesFragment = JokesFragment()
    private val webFragment = WebFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            replaceFragment(jokesFragment)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_jokes -> {
                    title = "Jokes"
                    replaceFragment(jokesFragment)
                }

                R.id.nav_web -> {
                    title = "Api info"
                    replaceFragment(webFragment)
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }
}


