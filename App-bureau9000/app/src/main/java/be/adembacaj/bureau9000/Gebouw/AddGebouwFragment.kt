package be.adembacaj.bureau9000.Gebouw

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.api.Api_Gebouw
import be.adembacaj.bureau9000.api.Api_Project
import be.adembacaj.bureau9000.api.Gebouw
import be.adembacaj.bureau9000.api.Project
import be.adembacaj.bureau9000.databinding.FragmentAddGebouwBinding
import be.adembacaj.bureau9000.databinding.FragmentGebouwBinding
import kotlinx.android.synthetic.main.fragment_add_gebouw.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGebouwFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddGebouwBinding.inflate(inflater)

        // to get the ID of the current project
        val idOfCurrentProject = arguments?.getInt("idOfCurrentProject")

        binding.buttonAddGebouw.setOnClickListener{

            val gebouwToAdd = Gebouw (
                naam = binding.editTextNaamGebouw.text.toString(),
                adres = binding.editTextAdresGebouw.text.toString(),
                postcode = Integer.parseInt(binding.editTextPostcodeGebouw.text.toString()),
                gemeente = binding.editTextGemeenteGebouw.text.toString(),
                hoogte = Integer.parseInt(binding.editTextHoogteGebouw.text.toString()),
                functie = binding.spinnerFunctieGebouw.selectedItem.toString(),
                projectId = idOfCurrentProject!!
            )

            Api_Gebouw.retrofitService.addGebouw(gebouwToAdd).enqueue(object : Callback<Gebouw>{

                override fun onResponse(call: Call<Gebouw>, response: Response<Gebouw>) {

                    val bundle = Bundle()
                    bundle.putInt("idOfCurrentProject", idOfCurrentProject)

                    //navigate to addRisicosToGebouw
                    view?.findNavController()
                        ?.navigate(be.adembacaj.bureau9000.R.id.action_addGebouwFragment_to_gebouwFragment, bundle)
                }

                override fun onFailure(call: Call<Gebouw>, t: Throwable) {
                    Toast.makeText(context, "error,try again later", Toast.LENGTH_SHORT).show()
                }
            })

        }

        return binding.root
    }
}