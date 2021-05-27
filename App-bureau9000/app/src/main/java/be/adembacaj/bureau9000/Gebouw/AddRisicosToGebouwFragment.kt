package be.adembacaj.bureau9000.Gebouw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.R
import be.adembacaj.bureau9000.api.*
import be.adembacaj.bureau9000.databinding.FragmentAddGebouwBinding
import be.adembacaj.bureau9000.databinding.FragmentAddRisicosToGebouwBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRisicosToGebouwFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddRisicosToGebouwBinding.inflate(inflater)

        val idOfCurrentProject = arguments?.getInt("idOfCurrentProject")
        val idOfCurrentGebouw = arguments?.getInt("idOfCurrentGebouw")

        Api_GebouwRisico.retrofitService.getRisicos()
            .enqueue(object : Callback<ArrayList<GebouwRisico>> {
                override fun onResponse(
                    call: Call<ArrayList<GebouwRisico>>,
                    response: Response<ArrayList<GebouwRisico>>
                ) {
                    val risicos = mutableListOf<GebouwRisico>()

                    if (response.body().isNullOrEmpty()) {
                        Toast.makeText(
                            context,
                            "Connection met DB, niet gelukt",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        risicos.addAll(response.body()!!)

                        val adapter =
                            ArrayAdapter(context!!, android.R.layout.simple_list_item_1, risicos)
                        binding.ListViewRisicoToevoegenGebouw.adapter = adapter

                        binding.ListViewRisicoToevoegenGebouw.setOnItemClickListener { myAdapter, myView, myItemInt, mylng ->
                            val selectedGebouwRisicoFromList =
                                binding.ListViewRisicoToevoegenGebouw.getItemAtPosition(myItemInt)

                            // id van risico
                            val IdRisico =
                                selectedGebouwRisicoFromList.toString().substringBefore(" -")
                            binding.TextViewIdSelectedRisico.text = IdRisico

                            // display only beschrijving van risico
                            val BeschrijvingRisico =
                                selectedGebouwRisicoFromList.toString().substringAfter("- ")
                                    .substringBefore(" :")
                            binding.TextViewGebouwRisicoBesc.text = BeschrijvingRisico

                            // display only the W van risico
                            val WRisico =
                                selectedGebouwRisicoFromList.toString().substringAfter(" : ")
                            binding.TextViewGebouwRisicoW.text = WRisico


                            binding.buttonGebouwRisicoToevoegen.setOnClickListener {
                                if (binding.TextViewGebouwRisicoBesc.text.isNotEmpty() &&
                                    binding.TextViewGebouwRisicoW.text.isNotEmpty()
                                ) {

                                    val currentGebouwRisicoGebouw = GebouwRisicoGebouw(
                                        gebouwId = idOfCurrentGebouw!!,
                                        gebouwRisicoId = Integer.parseInt(binding.TextViewIdSelectedRisico.text.toString())
                                    )

                                    Api_GebouwRisicoGebouw.retrofitService.addGebouwRisicoGebouw(
                                        currentGebouwRisicoGebouw
                                    )
                                        .enqueue(object : Callback<GebouwRisicoGebouw> {
                                            override fun onResponse(
                                                call: Call<GebouwRisicoGebouw>,
                                                response: Response<GebouwRisicoGebouw>
                                            ) {
                                                Toast.makeText(
                                                    context,
                                                    "Risico werd succesvol toegevoegd aan het gebouw",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                val bundle = Bundle()
                                                bundle.putInt("idOfCurrentGebouw", idOfCurrentGebouw)
                                                bundle.putInt("idOfCurrentProject", idOfCurrentProject!!)

                                                view?.findNavController()
                                                    ?.navigate(
                                                        R.id.action_addRisicosToGebouwFragment_to_risicosGebouwFragment,
                                                        bundle
                                                    )
                                            }

                                            override fun onFailure(
                                                call: Call<GebouwRisicoGebouw>,
                                                t: Throwable
                                            ) {
                                                Toast.makeText(
                                                    context,
                                                    "Cannot add Risico to gebouw, please try again later",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }

                                        }

                                        )
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<GebouwRisico>>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Connection met DB, niet gelukt",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        return binding.root
    }
}