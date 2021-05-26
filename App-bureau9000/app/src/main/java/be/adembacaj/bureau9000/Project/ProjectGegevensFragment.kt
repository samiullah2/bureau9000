package be.adembacaj.bureau9000.Project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.R
import be.adembacaj.bureau9000.api.Api_Project
import be.adembacaj.bureau9000.api.Project
import be.adembacaj.bureau9000.databinding.FragmentProjectGegevensBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectGegevensFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectGegevensBinding.inflate(inflater)

        Api_Project.retrofitService.getOneProject().enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                binding.editTextNaam.setText(response.body()?.naam)
                //binding.editTextContactPersoonInfo.setText(response.body()?.contactPersoon)
                //binding.editTextTelefoonContactPersoonInfo.setText(response.body()?.telefoonContactPersoon)
            }

            override fun onFailure(call: Call<Project>, t: Throwable) {
                Toast.makeText(context, "error,try again later", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonGebouwen.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_projectGegevensFragment_to_gebouwFragment)
        }

        return binding.root
    }
}