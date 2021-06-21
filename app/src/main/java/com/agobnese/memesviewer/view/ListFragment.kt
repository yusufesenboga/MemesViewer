package com.agobnese.memesviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agobnese.memesviewer.MemesListAdapter
import com.agobnese.memesviewer.R
import com.agobnese.memesviewer.model.MemesContainer
import com.agobnese.memesviewer.viewmodel.MemesViewerViewModel
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    private lateinit var viewModel: MemesViewerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MemesViewerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMemesContainer()
        viewModel.memesContainerLiveData.observe(viewLifecycleOwner, Observer {
            createTheList(it)
        })

    }

    fun createTheList(memesContainer: MemesContainer) {
        val adapter = MemesListAdapter(memesContainer) { position ->
            val direction = ListFragmentDirections.actionListFragmentToDetailsFragment(position)
            findNavController().navigate(direction)
        }
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
    }
}