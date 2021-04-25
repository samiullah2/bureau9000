package be.adembacaj.bureau9000

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.databinding.FragmentAddProjectBinding

class AddProjectFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddProjectBinding.inflate(inflater)

        binding.buttonCreateProject.setOnClickListener{
            val naamProject = binding.editTextNaamProject.text
            val bundle = bundleOf("nieuwProject" to naamProject)
            view?.findNavController()
                ?.navigate(R.id.action_addProjectFragment_to_projectFragment, bundle)
        }

        return binding.root
    }
}