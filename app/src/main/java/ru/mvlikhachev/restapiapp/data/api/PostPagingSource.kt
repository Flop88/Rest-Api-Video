package ru.mvlikhachev.restapiapp.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.mvlikhachev.restapiapp.data.api.model.PostResponse
import java.io.IOException

class PostPagingSource(
    private val newsApiService: PostService,
): PagingSource<Int, PostResponse>() {
    override fun getRefreshKey(state: PagingState<Int, PostResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostResponse> {
        return try {
            val page = params.key ?: 1
            val limit = 20
            val response = newsApiService.getPagingAllPosts(page = page, limit = limit).body() ?: emptyList()
            val nextKey = if (response.isEmpty()) null else response.size.plus(page).plus(1)
            val prevKey = if (page == 1) null else response.size.minus(limit)
            Log.d("checkData", "page: $page, prevKey: $prevKey, nextKey: $nextKey")
            LoadResult.Page(
                data = response,
                nextKey = nextKey,
                prevKey = prevKey,
            )
//            LoadResult.Error(IOException())
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}