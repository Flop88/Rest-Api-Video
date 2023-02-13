package ru.mvlikhachev.restapiapp.domain.usecases

import okhttp3.ResponseBody
import ru.mvlikhachev.restapiapp.data.PostRepository
import ru.mvlikhachev.restapiapp.data.api.model.PostResponse
import javax.inject.Inject

class PostPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun invoke(body: PostResponse) = postRepository.postPost(body = body)
}