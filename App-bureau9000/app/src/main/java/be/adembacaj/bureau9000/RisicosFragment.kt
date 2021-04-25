package be.adembacaj.bureau9000

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import be.adembacaj.bureau9000.databinding.FragmentRisicosBinding

class RisicosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRisicosBinding.inflate(inflater)

        if (arguments?.get("selectedFromList") != null){
            val risico = arguments?.get("selectedFromList")
            binding.textViewRisicoBesc.text = risico.toString()
        }

        // RISICO TOEVOEGEN --> WORDT ONTHOUDEN ALS OBJECT ZODAT WE AAN DE DATA KUNNEN !

        binding.btnKiesRisico.setOnClickListener{
                view?.findNavController()
                        ?.navigate(R.id.action_risicosFragment_to_kiesRisicoFragment)
    }

        return binding.root
    }
}