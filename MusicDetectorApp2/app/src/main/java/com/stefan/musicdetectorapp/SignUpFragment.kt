package com.stefan.musicdetectorapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.stefan.musicdetectorapp.databinding.ActivitySignUpBinding
import com.stefan.musicdetectorapp.databinding.FragmentLoginBinding
import com.stefan.musicdetectorapp.databinding.FragmentSignUpBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    private lateinit var fragmentSignUpBinding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentSignUpBinding.btnSignup.setOnClickListener{
            val email = fragmentSignUpBinding.txtEmail.text.toString()
            val password = fragmentSignUpBinding.txtPassword.text.toString()
            if (email != null && email != null && password != null) {
                val intent = Intent(activity, MainActivity::class.java)
                Snackbar.make(fragmentSignUpBinding.btnSignup,resources.getString(R.string.successSignUp), Snackbar.LENGTH_SHORT).show()
                intent.putExtra(Intent.EXTRA_TEXT, email)
                startActivity(intent)
            } else{
                if (email.trim().isEmpty()) {
                    fragmentSignUpBinding.txtEmail.error = resources.getString(R.string.msg_err_email_empty)
                }
                if (password.trim().isEmpty()) {
                    fragmentSignUpBinding.txtPassword.error = resources.getString(R.string.msg_err_pass_empty)
                }
                Snackbar.make(fragmentSignUpBinding.btnSignup, resources.getString(R.string.msg_failed_signup), Snackbar.LENGTH_LONG).show()
            }
        }

        fragmentSignUpBinding.tvToLogin.setOnClickListener {
            val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container,LoginFragment.newInstance())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        return fragmentSignUpBinding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }
}
