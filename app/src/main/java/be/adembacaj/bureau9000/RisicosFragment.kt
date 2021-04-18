package be.adembacaj.bureau9000

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import be.adembacaj.bureau9000.databinding.FragmentRisicosBinding

class RisicosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRisicosBinding.inflate(inflater)

        //code


        return binding.root
    }
}