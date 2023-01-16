package com.stefan.musicdetectorapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.stefan.musicdetectorapp.databinding.ActivityMainBinding
import com.stefan.musicdetectorapp.entity.SongViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val GOOGLE_SIGN_IN = 1001

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: SongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        viewModel = ViewModelProvider(this).get(SongViewModel::class.java)

        // Observe the authenticated user and update the UI accordingly
        auth.currentUser?.let {
            viewModel.fetchSavedSongs(it.uid)
            updateUI(it)
        } ?: updateUI(null)

        // Set click listener for Google sign in button
        binding.googleSignInButton.setOnClickListener {
            val signInIntent = auth.getProviderClient(GoogleAuthProvider.PROVIDER_ID).signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
        }

        // Set click listener for sign out button
        binding.signOutButton.setOnClickListener {
            auth.signOut()
            updateUI(null)
        }

        // Observe the list of saved songs and update the UI accordingly
        viewModel.allSongs.observe(this, Observer { songs ->
            if (songs.isNotEmpty()) {
                // Display the list of saved songs
                val fragment = SongListFragment.newInstance(songs)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            } else {
                // Display a message indicating that there are no saved songs
                val fragment = SongDetailFragment.newInstance(getString(R.string.no_saved_songs))
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
        })
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // If user is signed in, show the list of saved songs or a message indicating that there are no saved songs
            binding.googleSignInButton.isVisible = false
            binding.signOutButton.isVisible = true
            viewModel.fetchSavedSongs(user.uid)
        } else {
            // If user is not signed in, show the sign in button
            binding.googleSignInButton.isVisible = true
            binding.signOutButton.isVisible = false
            val fragment = SongDetailFragment.newInstance(getString(R.string.sign_in_prompt))
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val googleAuthCredential = GoogleAuthProvider.getCredential(task.getResult(
                    ApiException::class.java)?.idToken, null)
                auth.signInWithCredential(googleAuthCredential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Login successful, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT)
                            .show()
                        updateUI(null)
                    }
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                updateUI(null)
            }
        }
    }
}