package ru.mvlikhachev.restapiapp.domain.usecases

import ru.mvlikhachev.restapiapp.data.PostRepository
import ru.mvlikhachev.restapiapp.data.api.model.PostResponse
import javax.inject.Inject

class PutPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun invoke(id: String, body: PostResponse) = postRepository.putPost(id = id, body = body)
}