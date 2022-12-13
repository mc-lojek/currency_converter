package pl.mclojek.currency_converter.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import pl.mclojek.currency_converter.R
import pl.mclojek.currency_converter.databinding.FragmentRateDetailsBinding
import pl.mclojek.currency_converter.databinding.FragmentRateListBinding
import pl.mclojek.currency_converter.presentation.viewmodel.RateDetailsViewModel

class RateDetailsFragment : Fragment() {

    private val viewModel: RateDetailsViewModel by viewModels()
    private lateinit var binding: FragmentRateDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRateDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }
}