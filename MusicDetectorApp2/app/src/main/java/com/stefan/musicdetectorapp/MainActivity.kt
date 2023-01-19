package com.stefan.musicdetectorapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stefan.musicdetectorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this@MainActivity, SongListFragment::class.java)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.commit()

    }

}
