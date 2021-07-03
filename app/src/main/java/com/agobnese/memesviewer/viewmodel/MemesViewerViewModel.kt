package com.agobnese.memesviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agobnese.memesviewer.model.MemesContainerResult
import com.agobnese.memesviewer.repository.MemesRepository
import com.agobnese.memesviewer.repository.MemesRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemesViewerViewModel @Inject constructor(private val repo: MemesRepository) : ViewModel() {

    private val _memesContainerResultLiveData = repo.memeContainerResultLiveData
    val memesContainerResultLiveData: LiveData<MemesContainerResult>
        get() = _memesContainerResultLiveData

    init {
        fetchMemesContainer()
    }

    fun fetchMemesContainer() {
        _memesContainerResultLiveData.value = MemesContainerResult.isLoading
        viewModelScope.launch {
            repo.fetchMemesContainer()
        }
    }

}