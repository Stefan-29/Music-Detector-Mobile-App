package com.example.tubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")



        findViewById<TextView>(R.id.tv_email).text = email + "\n" + displayName

        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@HomeActivity , LoginActivity::class.java))
        }
    }
}