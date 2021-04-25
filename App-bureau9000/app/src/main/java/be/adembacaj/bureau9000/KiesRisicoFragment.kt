package be.adembacaj.bureau9000

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.adembacaj.bureau9000.api.Api_Risicobeschrijving
import be.adembacaj.bureau9000.api.Risicobeschrijving
import be.adembacaj.bureau9000.databinding.FragmentRisicosBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KiesRisicoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRisicosBinding.inflate(inflater)

        val listRISICOS = ArrayList<Risicobeschrijving>()

         Api_Risicobeschrijving.retrofitService.getRisicos()
            .enqueue(object : Callback<Risicobeschrijving> {
                override fun onResponse(
                    call: Call<Risicobeschrijving>, response: Response<Risicobeschrijving>
                ) {
                    listRISICOS.add(response.body()!!)
                }
                override fun onFailure(call: Call<Risicobeschrijving>, t: Throwable) {
                    Log.d("mmm", "Failure: " + t.message)
                }
            })

        Log.d("test", listRISICOS[0].toString())
        Log.d("test", listRISICOS.toString())

        return binding.root
    }
}