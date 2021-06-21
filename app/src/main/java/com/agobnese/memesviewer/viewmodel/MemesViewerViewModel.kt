package com.agobnese.memesviewer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agobnese.memesviewer.model.MemesContainer
import com.agobnese.memesviewer.network.MemesNetworkService
import com.agobnese.memesviewer.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemesViewerViewModel : ViewModel() {

    val memesContainerLiveData = MutableLiveData<MemesContainer>()

    fun fetchMemesContainer() {
        val client = RetrofitClient.retrofit?.create(MemesNetworkService::class.java)
        val fetchingCall = client?.getMemes()

        fetchingCall?.enqueue(object : Callback<MemesContainer> {
            override fun onResponse(
                call: Call<MemesContainer>,
                response: Response<MemesContainer>
            ) {
                val fetchedInfo = response.body()
                fetchedInfo.let {
                    memesContainerLiveData.value = it
                }
            }

            override fun onFailure(call: Call<MemesContainer>, t: Throwable) {
                Log.d("Application Tag", t.localizedMessage)
            }

        })
    }
}