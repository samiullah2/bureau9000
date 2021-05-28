package be.adembacaj.bureau9000.Gebouw

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.api.Api_GebouwRisicoGebouw
import be.adembacaj.bureau9000.api.GebouwRisico
import be.adembacaj.bureau9000.api.Project
import be.adembacaj.bureau9000.databinding.FragmentRisicosGebouwBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RisicosGebouwFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRisicosGebouwBinding.inflate(inflater)

        //get the ID of the current project and gebouw
        val idOfCurrentProject = arguments?.getInt("idOfCurrentProject")
        val idOfCurrentGebouw = arguments?.getInt("idOfCurrentGebouw")

        
        Api_GebouwRisicoGebouw.retrofitService.getrisicosByGebouw(idOfCurrentGebouw!!).
        enqueue(object : Callback<ArrayList<GebouwRisico>>{
            override fun onResponse(
                call: Call<ArrayList<GebouwRisico>>,
                response: Response<ArrayList<GebouwRisico>>
            ) {
                val risicosOfCurrentGebouw = mutableListOf<GebouwRisico>()

                if (response.body().isNullOrEmpty()) {
                    Toast.makeText(context,"Druk op de button om een risico toe te voegen", Toast.LENGTH_SHORT).show()
                }
                else {
                    risicosOfCurrentGebouw.addAll(response.body()!!)

                    val adapter = ArrayAdapter(context!!, R.layout.simple_list_item_1, risicosOfCurrentGebouw)
                    binding.ListViewRisicosGebouw.adapter = adapter

                }
            }

            override fun onFailure(call: Call<ArrayList<GebouwRisico>>, t: Throwable) {
                Toast.makeText(context,"Connection with DB, mislukt", Toast.LENGTH_SHORT).show()
            }
        })








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

