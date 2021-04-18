package be.adembacaj.bureau9000

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import be.adembacaj.bureau9000.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater)

        //code to check login
        // ....


        binding.buttonLogin.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment2_to_projectFragment)
        )

        return binding.root
    }
}