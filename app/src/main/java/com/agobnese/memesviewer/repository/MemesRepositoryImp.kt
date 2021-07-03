package com.agobnese.memesviewer.repository

import com.agobnese.memesviewer.model.MemesContainerResult

interface MemesRepositoryImp {
    suspend fun fetchMemesContainer()
}