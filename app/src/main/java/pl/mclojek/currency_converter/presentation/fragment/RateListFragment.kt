package pl.mclojek.currency_converter.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.mclojek.currency_converter.R
import pl.mclojek.currency_converter.databinding.FragmentRateListBinding
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.model.Rate
import pl.mclojek.currency_converter.presentation.adapter.RateRecyclerAdapter
import pl.mclojek.currency_converter.presentation.viewmodel.RateDetailsViewModel
import pl.mclojek.currency_converter.presentation.viewmodel.RateListViewModel
import java.time.LocalDate
import kotlin.random.Random

@AndroidEntryPoint
class RateListFragment : Fragment() {

    private val viewModel: RateListViewModel by viewModels()
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("dzialam sobie hehehe", "dskjsldja")
        viewModel.summariesLiveData.observe(viewLifecycleOwner) {
            Log.d("dzialam", "sobie fajnie")
            adapter.submitData(lifecycle, it)
        }
        Log.d("dzialam sobie hehehe", "2222222dskjsldja")
    }

}