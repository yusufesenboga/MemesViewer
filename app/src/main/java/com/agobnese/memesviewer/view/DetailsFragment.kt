package com.agobnese.memesviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.agobnese.memesviewer.R
import com.agobnese.memesviewer.model.MemesContainer
import com.agobnese.memesviewer.viewmodel.MemesViewerViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    lateinit var viewModel: MemesViewerViewModel
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = args.position
        viewModel = ViewModelProvider(requireActivity()).get(MemesViewerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.memesContainerLiveData.observe(viewLifecycleOwner, {
            setValuesForDetails(it,position)
        })
    }

    fun setValuesForDetails(memesContainer: MemesContainer, position: Int) {
        memeNameInDetails.text = memesContainer.data.memes[position].name
        Picasso.get().load(memesContainer.data.memes[position].url).into(memeImageInDetails)
    }

}