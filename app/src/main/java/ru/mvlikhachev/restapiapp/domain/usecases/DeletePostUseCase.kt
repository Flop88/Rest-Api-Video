package ru.mvlikhachev.restapiapp.domain.usecases

import ru.mvlikhachev.restapiapp.data.PostRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun invoke(id: String) = postRepository.deletePost(id = id)
}