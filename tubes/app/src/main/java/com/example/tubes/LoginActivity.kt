package com.example.tubes
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tubes.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        findViewById<Button>(R.id.btn_login).setOnClickListener{
            val email = R.id.txt_Email.toString()
            val password = R.id.txt_password.toString()
            if (email.trim().isEmpty() || password.trim().isEmpty()) {
                if (email.trim().isEmpty())
                    binding.txtLogin.error = resources.getString(R.string.msg_err_email_empty)
                if (password.trim().isEmpty())
                    binding.txtPassword.error = resources.getString(R.string.msg_err_pass_empty)
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        updateUIEmail()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            resources.getString(R.string.err_invalid_login),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        findViewById<TextView>(R.id.tv_to_signup).setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this@LoginActivity , gso)

        findViewById<ImageView>(R.id.img_google).setOnClickListener {
            signInGoogle()
        }

    }

    private fun updateUIEmail() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this@LoginActivity, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val intent : Intent = Intent(this@LoginActivity , HomeActivity::class.java)
            }else{
                Toast.makeText(this@LoginActivity, it.exception.toString() , Toast.LENGTH_SHORT).show()

            }
        }
    }


}