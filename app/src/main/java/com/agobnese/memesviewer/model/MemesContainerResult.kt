package com.agobnese.memesviewer.model

sealed class MemesContainerResult {
    class Success(val memesContainer: MemesContainer) : MemesContainerResult()
    class Fail(val error: Error) : MemesContainerResult()
    object isLoading : MemesContainerResult()

}