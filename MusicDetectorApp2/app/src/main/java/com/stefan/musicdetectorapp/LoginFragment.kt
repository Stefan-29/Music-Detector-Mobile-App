package com.stefan.musicdetectorapp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.stefan.musicdetectorapp.LoginFragment.Companion.newInstance
import com.stefan.musicdetectorapp.SignUpFragment.Companion.newInstance
import com.stefan.musicdetectorapp.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var fragmentLoginBinding: FragmentLoginBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        fragmentLoginBinding.btnLogin.setOnClickListener{
            val email = R.id.txt_Email.toString()
            val password = R.id.txt_password.toString()
            if (email.trim().isEmpty() || password.trim().isEmpty()) {
                if (email.trim().isEmpty())
                    fragmentLoginBinding.txtLogin.error = resources.getString(R.string.msg_err_email_empty)
                if (password.trim().isEmpty())
                    fragmentLoginBinding.txtPassword.error = resources.getString(R.string.msg_err_pass_empty)
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        updateUIEmail()
                    } else {
                        Toast.makeText(
                            activity,
                            resources.getString(R.string.err_invalid_login),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        fragmentLoginBinding.tvToSignup.setOnClickListener {
            val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,SignUpFragment.newInstance())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }!!

        fragmentLoginBinding.imgGoogle.setOnClickListener {
            signInGoogle()
        }
    }

    private fun updateUIEmail() {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container,SongListFragment.newInstance())
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
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
            Toast.makeText(activity, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container,SongListFragment.newInstance())
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            }else{
                Toast.makeText(activity, it.exception.toString() , Toast.LENGTH_SHORT).show()

            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}