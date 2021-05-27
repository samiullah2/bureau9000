package be.adembacaj.bureau9000.Gebouw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.R
import be.adembacaj.bureau9000.databinding.FragmentGebouwGegevensBinding

class GebouwGegevensFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGebouwGegevensBinding.inflate(inflater)

        // the stored IDS of the current project and gebouw
        val idOfCurrentProject = arguments?.getInt("idOfCurrentProject")
        val selectedGebouwFromListview = arguments?.getString("selectedGebouwFromListview").toString()
        val idOfCurrentGebouwString = selectedGebouwFromListview.substringBefore(" ", "error")
        val idOfCurrentGebouw = idOfCurrentGebouwString.toInt()


        // HIER ZOU DE ADVISEUR DE GEBOUWGEGEVENS KUNNEN AANPASSEN MOGE ER IETS FOUT GEWEEST ZIJN

//        Api_Project.retrofitService.getOneProject().enqueue(object : Callback<Project> {
//            override fun onResponse(call: Call<Project>, response: Response<Project>) {
//                binding.editTextNaam.setText(response.body()?.naam)
//                //binding.editTextContactPersoonInfo.setText(response.body()?.contactPersoon)
//                //binding.editTextTelefoonContactPersoonInfo.setText(response.body()?.telefoonContactPersoon)
//            }
//
//            override fun onFailure(call: Call<Project>, t: Throwable) {
//                Toast.makeText(context, "error,try again later", Toast.LENGTH_SHORT).show()
//            }
//        })



        binding.buttonRisicosGebouwen.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("idOfCurrentProject", idOfCurrentProject!!)
            bundle.putInt("idOfCurrentGebouw", idOfCurrentGebouw!!)

            view?.findNavController()?.navigate(R.id.action_gebouwGegevensFragment_to_risicosGebouwFragment, bundle)
        }

        return binding.root
    }
}