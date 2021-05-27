package be.adembacaj.bureau9000.Gebouw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.databinding.FragmentRisicosGebouwBinding

class RisicosGebouwFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRisicosGebouwBinding.inflate(inflater)

        //get the ID of the current project and gebouw
        val idOfCurrentProject = arguments?.getInt("idOfCurrentProject")
        val idOfCurrentGebouw = arguments?.getInt("idOfCurrentGebouw")

        









        binding.buttonAddRisicoToGebouw.setOnClickListener{

            // to save the ID of the current project and gebouw
            val bundle = Bundle()
            bundle.putInt("idOfCurrentProject", idOfCurrentProject!!)
            bundle.putInt("idOfCurrentGebouw", idOfCurrentGebouw!!)

            view?.findNavController()
                ?.navigate(be.adembacaj.bureau9000.R.id.action_risicosGebouwFragment_to_addRisicosToGebouwFragment, bundle)

        }

        //binding.buttonNextStep2.setOnClickListener() nog implementeren




        return binding.root
    }
}

