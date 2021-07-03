package com.agobnese.memesviewer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.agobnese.memesviewer.model.MemesContainerResult
import com.agobnese.memesviewer.network.MemesNetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class MemesRepository @Inject constructor(private val networkService: MemesNetworkService) :
    MemesRepositoryImp {

    val memeContainerResultLiveData = MutableLiveData<MemesContainerResult>()

    @Inject
    lateinit var retrofit: Retrofit

    override suspend fun fetchMemesContainer() {
        withContext(Dispatchers.IO) {
            val memesCall = networkService.getMemes()
            try {
                val response = memesCall.execute()
                val data = response.body()

                data?.let { fetchedData ->
                    memeContainerResultLiveData.postValue(
                        MemesContainerResult.Success(
                            fetchedData
                        )
                    )
                }
            } catch (e: Exception) {
                Log.d("ApplicationTag", e.localizedMessage)
                memeContainerResultLiveData.postValue(MemesContainerResult.Fail(Error(e)))
            }
        }
    }
}