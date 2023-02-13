package ru.mvlikhachev.restapiapp.data

import ru.mvlikhachev.restapiapp.data.api.RemoteDataSource
import ru.mvlikhachev.restapiapp.data.api.model.PostResponse
import ru.mvlikhachev.restapiapp.utils.BaseApiResponse
import ru.mvlikhachev.restapiapp.utils.NetworkResult
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BaseApiResponse(){

    suspend fun getAllPosts(): NetworkResult<List<PostResponse>> {
        return safeApiCall { remoteDataSource.getAllPosts() }
    }

    suspend fun postPost(body: PostResponse): NetworkResult<PostResponse> {
        return safeApiCall { remoteDataSource.postPosts(body = body) }
    }

    suspend fun putPost(id: String, body: PostResponse): NetworkResult<PostResponse> {
        return safeApiCall { remoteDataSource.putPosts(id = id, body = body) }
    }

    suspend fun patchPost(id: String, body: PostResponse): NetworkResult<PostResponse> {
        return safeApiCall { remoteDataSource.patchPosts(id = id, body = body) }
    }

    suspend fun deletePost(id: String): NetworkResult<PostResponse> {
        return safeApiCall { remoteDataSource.deletePosts(id = id) }
    }
}