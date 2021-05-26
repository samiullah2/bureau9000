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
import be.adembacaj.bureau9000.databinding.FragmentGebouwBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GebouwFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGebouwBinding.inflate(inflater)

        //get the ID of the current project
        val idOfCurrentProject = arguments?.getInt("idOfCurrentProject")

        Api_Gebouw.retrofitService.getGebouwen().enqueue(object : Callback<ArrayList<Gebouw>>
        {
            override fun onResponse(
                call: Call<ArrayList<Gebouw>>,
                response: Response<ArrayList<Gebouw>>
            ) {
                val gebouwen = mutableListOf<Gebouw>()

                if (response.body().isNullOrEmpty()) {
                    Toast.makeText(context,"Druk op de button om een nieuw gebouw toe te voegen", Toast.LENGTH_SHORT).show()
                }
                else {
                    gebouwen.addAll(response.body()!!)

                    val adapter = ArrayAdapter(context!!, R.layout.simple_list_item_1, gebouwen)
                    binding.ListViewGebouwen.adapter = adapter

                    binding.ListViewGebouwen.setOnItemClickListener { myAdapter, myView, myItemInt, mylng ->
                        val selectedFromList = binding.ListViewGebouwen.getItemAtPosition(myItemInt)

                        val bundle = Bundle()
                        bundle.putString("selectedFromList", selectedFromList.toString())

                        // go to info wijzigen ?
//                        view?.findNavController()
//                            ?.navigate(be.adembacaj.bureau9000.R.id.action_projectFragment_to_projectGegevensFragment, bundle)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Gebouw>>, t: Throwable) {
                Toast.makeText(context, "error,try again later", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonNieuwGebouw.setOnClickListener {

            // to save the ID of the current project
            val bundle = Bundle()
            bundle.putInt("idOfCurrentProject", idOfCurrentProject!!)

            Navigation.createNavigateOnClickListener(be.adembacaj.bureau9000.R.id.action_gebouwFragment_to_addGebouwFragment, bundle)
        }

        return binding.root
    }
}