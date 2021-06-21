package com.agobnese.memesviewer.viewmodel

import androidx.lifecycle.ViewModel
import com.agobnese.memesviewer.model.MemesContainer
import com.agobnese.memesviewer.network.MemesNetworkService
import com.agobnese.memesviewer.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemesViewerViewModel : ViewModel() {


    fun fetchMemesContainer(): MemesContainer? {

        val client = RetrofitClient.retrofit?.create(MemesNetworkService::class.java)
        val fetchingCall = client?.getMemes()

            val response = fetchingCall?.execute()
            val fetchedInfo = response?.body()

        return fetchedInfo

    }
}