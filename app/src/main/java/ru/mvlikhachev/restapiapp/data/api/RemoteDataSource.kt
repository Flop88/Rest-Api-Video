package ru.mvlikhachev.restapiapp.data.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.mvlikhachev.restapiapp.data.api.model.PostResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postService: PostService) {

    suspend fun getAllPosts() = postService.getAllPosts()

    fun getPagingAllPost() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { PostPagingDataSoruce(postService = postService) }
    ).flow

    suspend fun postPosts(body: PostResponse) = postService.postPost(body = body)
    suspend fun putPosts(id: String, body: PostResponse) = postService.putPost(id = id, body = body)
    suspend fun patchPosts(id: String, body: PostResponse) = postService.patchPost(id = id, body = body)
    suspend fun deletePosts(id: String) = postService.deletePost(id = id)

}