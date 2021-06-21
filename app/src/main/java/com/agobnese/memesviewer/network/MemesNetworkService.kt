package com.agobnese.memesviewer.network

import com.agobnese.memesviewer.model.MemesContainer
import retrofit2.Call
import retrofit2.http.GET

interface MemesNetworkService {

    //https://api.imgflip.com/get_memes

    @GET("get_memes")
    fun getMemes(): Call<MemesContainer>
}