package be.adembacaj.bureau9000

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import be.adembacaj.bureau9000.databinding.FragmentProjectBinding


class ProjectFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectBinding.inflate(inflater)

        // some code

        var nieuwProject = ""
        if (arguments?.getString("nieuwProject").toString() != ""){
            nieuwProject = arguments?.getString("nieuwProject").toString()

        }

        val projecten = ArrayList<String>()
        projecten.add("PROXIMUS")
        projecten.add("TELENET")
        projecten.add("ODISEE")
        projecten.add("GALAXY VIEBS")
        if (nieuwProject != ""){
            projecten.add(nieuwProject)
        }

        // Adding items to listview
        val adapter = this.context?.let { ArrayAdapter(it, R.layout.simple_list_item_1, projecten) }
        binding.ListViewProjecten.adapter = adapter

        binding.buttonNieuwProject.setOnClickListener{
            view?.findNavController()
                ?.navigate(be.adembacaj.bureau9000.R.id.action_projectFragment_to_addProjectFragment)
        }


        return binding.root
    }
}