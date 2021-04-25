package be.adembacaj.bureau9000

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.adembacaj.bureau9000.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater)

        //code to check login
        // ....


        //binding.buttonLogin.setOnClickListener()


        return binding.root
    }
}