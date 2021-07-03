package com.agobnese.memesviewer.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agobnese.memesviewer.MemesListAdapter
import com.agobnese.memesviewer.R
import com.agobnese.memesviewer.model.MemesContainer
import com.agobnese.memesviewer.model.MemesContainerResult
import com.agobnese.memesviewer.viewmodel.MemesViewerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_fragment.*

@AndroidEntryPoint
class ListFragment : Fragment() {

    val viewModel: MemesViewerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.memesContainerResultLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { memesContainerResult ->

                when (memesContainerResult) {
                    is MemesContainerResult.Success -> {
                        createTheList(memesContainerResult.memesContainer)
                    }
                    MemesContainerResult.isLoading -> {

                    }
                    is MemesContainerResult.Fail -> {
                        couldntFetchFromInternetError()
                    }
                }
            }
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

    fun couldntFetchFromInternetError() {
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Couldn't Connect To Internet")
            .setMessage(
                "Your phone doesn't seem to be connected to internet, " +
                        "please try again to connect to the internet"
            )
            .setPositiveButton(
                "Try Again",
                { dialog, which -> viewModel.fetchMemesContainer() })
            .setNegativeButton("Quit", { dialog, which -> System.exit(0) })
            .show()
    }
}