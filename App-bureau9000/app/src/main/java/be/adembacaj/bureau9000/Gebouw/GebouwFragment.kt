package be.adembacaj.bureau9000.Gebouw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.adembacaj.bureau9000.databinding.FragmentGebouwBinding

class GebouwFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGebouwBinding.inflate(inflater)

//        Api_Project.retrofitService.getOneProject().enqueue(object : Callback<Project> {
//            override fun onResponse(call: Call<Project>, response: Response<Project>) {
//                binding.editTextNaam.setText(response.body()?.naam)
//                binding.editTextContactPersoonInfo.setText(response.body()?.contactPersoon)
//                binding.editTextTelefoonContactPersoonInfo.setText(response.body()?.telefoonContactPersoon)
//            }
//
//            override fun onFailure(call: Call<Project>, t: Throwable) {
//                Toast.makeText(context, "error,try again later", Toast.LENGTH_SHORT).show()
//            }
//        })

        //binding.buttonNieuwGebouw.setOnClickListener()

        return binding.root
    }
}