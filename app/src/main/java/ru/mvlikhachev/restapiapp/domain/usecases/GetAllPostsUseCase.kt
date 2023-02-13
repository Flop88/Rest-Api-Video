package ru.mvlikhachev.restapiapp.domain.usecases

import ru.mvlikhachev.restapiapp.data.PostRepository
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun invoke() = postRepository.getAllPosts()
}