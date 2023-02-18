package ru.mvlikhachev.restapiapp.data.api

import androidx.paging.Pager
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import ru.mvlikhachev.restapiapp.data.api.model.PostResponse

interface PostService {

    @GET("/posts")
    suspend fun getAllPosts(): Response<List<PostResponse>>

    @GET("/posts")
    suspend fun getPagingAllPosts(@Query("_start") pager: Int, @Query("_limit") limit: Int): Response<List<PostResponse>>

    @POST("/posts")
    suspend fun postPost(@Body body: PostResponse): Response<PostResponse>

    @PUT("/posts/{id}")
    suspend fun putPost(@Path("id") id: String, @Body body: PostResponse): Response<PostResponse>

    @PATCH("/posts/{id}")
    suspend fun patchPost(@Path("id") id: String, @Body body: PostResponse): Response<PostResponse>

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: String): Response<PostResponse>

}