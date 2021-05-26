package be.adembacaj.bureau9000.Project

import android.R as RLayout
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.R
import be.adembacaj.bureau9000.api.Api_Project
import be.adembacaj.bureau9000.api.Project
import be.adembacaj.bureau9000.databinding.FragmentProjectBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectBinding.inflate(inflater)

        Api_Project.retrofitService.getProjects().enqueue(object : Callback<ArrayList<Project>> {
            override fun onResponse(call: Call<ArrayList<Project>>, response: Response<ArrayList<Project>>) {

                val projecten = mutableListOf<Project>()

                if (response.body().isNullOrEmpty()) {
                    Toast.makeText(context,"Druk op de button om een nieuw project toe te voegen", Toast.LENGTH_SHORT).show()
                }
                else {
                    projecten.addAll(response.body()!!)

                    val adapter = ArrayAdapter(context!!, RLayout.layout.simple_list_item_1, projecten)
                    binding.ListViewProjecten.adapter = adapter

                    binding.ListViewProjecten.setOnItemClickListener { myAdapter, myView, myItemInt, mylng ->
                        val selectedFromList = binding.ListViewProjecten.getItemAtPosition(myItemInt)

                        val bundle = Bundle()
                        bundle.putString("selectedFromList", selectedFromList.toString())

                        view?.findNavController()
                            ?.navigate(R.id.action_projectFragment_to_projectGegevensFragment, bundle)
                    }
                }

            }

            override fun onFailure(call: Call<ArrayList<Project>>, t: Throwable) {
                Toast.makeText(context, "error,try again later", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonNieuwProject.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_projectFragment_to_addProjectFragment)
        )

        return binding.root
    }
}