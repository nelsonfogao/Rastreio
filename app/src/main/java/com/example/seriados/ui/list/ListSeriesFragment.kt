package com.example.seriados.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seriados.R
import com.example.seriados.database.AppDatabase
import com.example.seriados.database.SeriesUtil
import kotlinx.android.synthetic.main.fragment_series.*

class ListSeriesFragment : Fragment() {
    private lateinit var listSeriesViewModel: ListSeriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_series, container, false)


        val appDatabase = AppDatabase.getInstance(requireContext().applicationContext)

        val seriesDao = appDatabase.seriesDao()

        val listSeriesViewModelFactory = ListSeriesViewModelFactory(seriesDao)
        listSeriesViewModel = ViewModelProvider(this, listSeriesViewModelFactory)
            .get(ListSeriesViewModel::class.java)


        listSeriesViewModel.series.observe(viewLifecycleOwner) {
            listViewSeries.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                it
            )
            listViewSeries.setOnItemClickListener { parent, view, position, id ->
                val series = it.get(position)
                SeriesUtil.serieSelecionada = series
                findNavController().navigate(R.id.formSeriesFragment)
            }
        }
        listSeriesViewModel.atualizarListaSeries()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fabFormSeries.setOnClickListener {
            SeriesUtil.serieSelecionada = null
            findNavController().navigate(R.id.formSeriesFragment)
        }
    }

}