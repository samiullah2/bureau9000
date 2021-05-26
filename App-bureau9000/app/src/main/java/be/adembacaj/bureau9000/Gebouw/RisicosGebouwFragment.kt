package be.adembacaj.bureau9000.Gebouw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.adembacaj.bureau9000.databinding.FragmentRisicosGebouwBinding

class RisicosGebouwFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRisicosGebouwBinding.inflate(inflater)






        return binding.root
    }
}

