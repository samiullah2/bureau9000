package be.adembacaj.bureau9000.Project

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.R
import be.adembacaj.bureau9000.api.Api_Project
import be.adembacaj.bureau9000.api.ProjectEnOpdrachtgever
import be.adembacaj.bureau9000.databinding.FragmentProjectGegevensBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectGegevensFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectGegevensBinding.inflate(inflater)

        val selectedFromList = arguments?.getString("selectedFromList").toString()
        val idOfCurrentProjectString = selectedFromList.substringBefore(" ", "error")
        val idOfCurrentProject = idOfCurrentProjectString.toInt()


        // HIER ZOU DE ADVISEUR DE KLANTENGEGEVENS KUNNEN AANPASSEN MOGE ER IETS FOUT GEWEEST ZIJN

        Api_Project.retrofitService.getOneWholeProject(idOfCurrentProject).enqueue(object : Callback<ProjectEnOpdrachtgever> {
            override fun onResponse(call: Call<ProjectEnOpdrachtgever>, response: Response<ProjectEnOpdrachtgever>) {

                binding.editTextNaamProject.setText(response.body()?.naam)
                binding.editTextNaamOpdrachtGever.setText(response.body()?.opdrachtgeverNaam)
                binding.editTextAdresOpdrachtgever.setText(response.body()?.adres)
                binding.editTextPostcodeOpdrachtGever.setText(response.body()?.postcode.toString())
                binding.editTextGemeenteOpdrachtGever.setText(response.body()?.gemeente)
                binding.editTextContactOpdrachtGever.setText(response.body()?.email)
                binding.editTextNaamKlant.setText(response.body()?.klantNaam)

            }

            override fun onFailure(call: Call<ProjectEnOpdrachtgever>, t: Throwable) {
                Toast.makeText(context, "error,try again later", Toast.LENGTH_SHORT).show()
            }
        })

        // nog te implementeren
//        binding.buttonProjectGegevensUpdaten.setOnClickListener{
//              BIJ HET IMPLEMENTEREN MOET JE DE POSTCODE WEER IN INT CONVERTEREN WANT STAAT VOORLOPIG IN STRING
//        }

        binding.buttonGebouwen.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("idOfCurrentProject", idOfCurrentProject)

            view?.findNavController()?.navigate(R.id.action_projectGegevensFragment_to_gebouwFragment, bundle)
        }

        return binding.root
    }
}