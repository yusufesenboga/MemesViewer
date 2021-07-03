package com.agobnese.memesviewer.repository

import androidx.lifecycle.MutableLiveData
import com.agobnese.memesviewer.model.Data
import com.agobnese.memesviewer.model.MemesContainer
import com.agobnese.memesviewer.model.MemesContainerResult
import org.junit.Before
import org.junit.Test

class MemesRepositoryTest : MemesRepositoryImp{

    private val memes = mutableListOf<Data>()
    private val fakeMemesItems = MutableLiveData<List<Data>>(memes)

    @Before
    fun setUp() {

    }

    @Test
    override suspend fun fetchMemesContainer() {
        fakeMemesItems
    }
}