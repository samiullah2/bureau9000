package be.adembacaj.bureau9000.Project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.R
import be.adembacaj.bureau9000.api.Api_Opdrachtgever
import be.adembacaj.bureau9000.api.Api_Project
import be.adembacaj.bureau9000.api.Opdrachtgever
import be.adembacaj.bureau9000.api.Project
import be.adembacaj.bureau9000.databinding.FragmentAddProjectBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProjectFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddProjectBinding.inflate(inflater)

        binding.buttonNextStep.setOnClickListener {

            if (binding.editTextNaamProject.text.isNotEmpty()) {

                //Defining the project object as we need to pass with the call
                val project = Project(
                    naam = binding.editTextNaamProject.text.toString()
                )

                //calling the api
                Api_Project.retrofitService.addProject(project)
                    .enqueue(object : Callback<Project> {
                        override fun onResponse(
                            call: Call<Project>,
                            response: Response<Project>
                        ) {

                            val bundle = Bundle()
                            bundle.putString("projectNaam", project.naam)

                            //navigate to projectGegevens
                            view?.findNavController()
                                ?.navigate(R.id.action_addProjectFragment_to_addProjectGegevensFragment, bundle)
                        }

                        override fun onFailure(call: Call<Project>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "error,try again later",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

            } else {
                Toast.makeText(
                    this.context,
                    "please, enter all the information",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }
}

