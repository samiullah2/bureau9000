package be.adembacaj.bureau9000.Project

import android.os.Bundle
import android.os.StrictMode
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
import be.adembacaj.bureau9000.databinding.FragmentAddProjectGegevensBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddProjectGegevensFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddProjectGegevensBinding.inflate(inflater)


        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        val projectNaam = arguments?.getString("projectNaam").toString()

        binding.TextViewNaamKlant.visibility = View.GONE;
        binding.editTextNaamKlant.visibility = View.GONE;

        binding.checkBoxKlantSame.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                //if it is check
                binding.TextViewNaamKlant.visibility = View.VISIBLE;
                binding.editTextNaamKlant.visibility = View.VISIBLE;

            } else {
                binding.TextViewNaamKlant.visibility = View.GONE;
                binding.editTextNaamKlant.visibility = View.GONE;
            }
        };

        binding.buttonCreateProject.setOnClickListener {

            if (binding.editTextNaamOpdrachtGever.text.isNotEmpty() &&
                binding.editTextAdresOpdrachtgever.text.isNotEmpty() &&
                binding.editTextPostcodeOpdrachtGever.text.isNotEmpty() &&
                binding.editTextGemeenteOpdrachtGever.text.isNotEmpty() &&
                binding.editTextContactOpdrachtGever.text.isNotEmpty()
            ) {

               val projectId = Api_Project.retrofitService.getOneProjectByName(projectNaam).execute().body()

                val opdrachtgever = Opdrachtgever(
                    naam = binding.editTextNaamOpdrachtGever.text.toString(),
                    adres = binding.editTextAdresOpdrachtgever.text.toString(),
                    postcode = Integer.parseInt(binding.editTextPostcodeOpdrachtGever.text.toString()),
                    gemeente = binding.editTextGemeenteOpdrachtGever.text.toString(),
                    email = binding.editTextContactOpdrachtGever.text.toString(),
                    klantNaam = binding.editTextNaamKlant.text.toString(),
                    projectId = projectId?.id!!
                )

                Api_Opdrachtgever.retrofitService.addOpdrachtgeverToProject(opdrachtgever)
                    .enqueue(object : Callback<Opdrachtgever> {
                        override fun onResponse(
                            call: Call<Opdrachtgever>,
                            response: Response<Opdrachtgever>
                        ) {
                            Toast.makeText(
                                context,
                                "project is added",
                                Toast.LENGTH_SHORT
                            ).show()

                            //navigate to projects
                            view?.findNavController()
                                ?.navigate(R.id.action_addProjectGegevensFragment_to_projectFragment)
                        }

                        override fun onFailure(
                            call: Call<Opdrachtgever>,
                            t: Throwable
                        ) {
                            Toast.makeText(
                                context,
                                "error,try again later",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
            else {
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

