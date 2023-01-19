package com.example.tubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tubes.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignup.setOnClickListener{
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            if (email != null && email != null && password != null) {
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                Snackbar.make(this,binding.btnSignup,resources.getString(R.string.successSignUp), Snackbar.LENGTH_SHORT).show()
                intent.putExtra(Intent.EXTRA_TEXT, email)
                startActivity(intent)
                this.finish()
            } else{
                if (email.trim().isEmpty()) {
                    binding.txtEmail.error = resources.getString(R.string.msg_err_email_empty)
                }
                if (password.trim().isEmpty()) {
                    binding.txtPassword.error = resources.getString(R.string.msg_err_pass_empty)
                }
                Snackbar.make(this, binding.btnSignup, resources.getString(R.string.msg_failed_signup), Snackbar.LENGTH_LONG).show()
            }
        }

        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}