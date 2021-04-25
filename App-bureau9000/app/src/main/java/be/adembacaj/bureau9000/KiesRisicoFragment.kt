package be.adembacaj.bureau9000

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import be.adembacaj.bureau9000.api.Api_Risicobeschrijving
import be.adembacaj.bureau9000.api.Risicobeschrijving
import be.adembacaj.bureau9000.databinding.FragmentKiesRisicoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class KiesRisicoFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentKiesRisicoBinding.inflate(inflater)

        val listRISICOS = ArrayList<Risicobeschrijving>()

        Api_Risicobeschrijving.retrofitService.getRisicos()
                .enqueue(object : Callback<ArrayList<Risicobeschrijving>> {
                    override fun onResponse(
                            call: Call<ArrayList<Risicobeschrijving>>, response: Response<ArrayList<Risicobeschrijving>>
                    ) {
                        listRISICOS.addAll(response.body()!!)

                        val adapter = ArrayAdapter(context!!, R.layout.simple_list_item_1, listRISICOS)
                        binding.listRisicos.adapter = adapter

                        binding.listRisicos.setOnItemClickListener(OnItemClickListener
                        { myAdapter, myView, myItemInt, mylng ->
                            val selectedFromList = binding.listRisicos.getItemAtPosition(myItemInt)

                            val bundle = bundleOf("selectedFromList" to selectedFromList)
                            view?.findNavController()
                                    ?.navigate(be.adembacaj.bureau9000.R.id.action_kiesRisicoFragment_to_risicosFragment, bundle)
                    })

                    }

                    override fun onFailure(call: Call<ArrayList<Risicobeschrijving>>, t: Throwable) {
                        Log.d("mmm", "Failure: " + t.message)
                    }
                })

        return binding.root
    }
}