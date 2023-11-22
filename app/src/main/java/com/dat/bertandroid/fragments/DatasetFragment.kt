package com.dat.bertandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dat.bertandroid.R
import com.dat.bertandroid.databinding.FragmentDatasetBinding
//import org.tensorflow.lite.examples.bertqa.R
//import org.tensorflow.lite.examples.bertqa.databinding.FragmentDatasetBinding
import com.dat.bertandroid.dataset.LoadDataSetClient

class DatasetFragment : Fragment() {

    private var _fragmentDatasetList: FragmentDatasetBinding? = null
    private val fragmentDatasetList get() = _fragmentDatasetList!!
    private var titles : List<String> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentDatasetList = FragmentDatasetBinding.inflate(inflater, container, false)

        return fragmentDatasetList.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val client = LoadDataSetClient(requireActivity())
        client.loadJson()?.let {
            titles = it.getTitles()
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
            val dataSetAdapter = DatasetAdapter(titles) {
                startQaScreen(it)
            }
            val linearLayoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(
                fragmentDatasetList.recyclerView.context,
                linearLayoutManager.orientation
            )
            with(fragmentDatasetList.recyclerView) {
                layoutManager = linearLayoutManager
                adapter = dataSetAdapter
                addItemDecoration(decoration)
            }
    }

    private fun startQaScreen(position: Int) {
        val action = DatasetFragmentDirections.actionDatasetFragmentToQaFragment(position)
        Navigation.findNavController(requireActivity(), R.id.fragment_container)
            .navigate(action)
    }
}
