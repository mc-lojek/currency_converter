package pl.mclojek.currency_converter.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.mclojek.currency_converter.R
import pl.mclojek.currency_converter.databinding.FragmentRateListBinding
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.model.Rate
import pl.mclojek.currency_converter.presentation.adapter.RateRecyclerAdapter
import pl.mclojek.currency_converter.presentation.viewmodel.RateDetailsViewModel
import java.time.LocalDate
import kotlin.random.Random

class RateListFragment : Fragment() {

    private val viewModel: RateDetailsViewModel by viewModels()
    private lateinit var binding: FragmentRateListBinding

    private val adapter = RateRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRateListBinding.inflate(inflater, container, false)

        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = adapter

        lifecycleScope.launch {
            for (i in 1..10) {
                delay(4000)
                adapter.addItem(
                    DailyRateSummary(
                        LocalDate.now(),
                        "USD",
                        listOf(Rate("GBP", Random.nextFloat()), Rate("PLN", Random.nextFloat()))
                    ),
                )
            }
        }

        return binding.root
    }
}