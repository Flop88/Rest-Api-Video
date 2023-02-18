package ru.mvlikhachev.restapiapp.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.mvlikhachev.restapiapp.data.api.model.PostResponse
import java.io.IOException

class PostPagingDataSoruce(
    private val postService: PostService
): PagingSource<Int, PostResponse>() {

    override fun getRefreshKey(state: PagingState<Int, PostResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostResponse> {
        return try {
            val page = params.key ?: 1
            val limit = 20
            val response = postService.getPagingAllPosts(pager = page, limit = limit).body() ?: emptyList()
            val nextKey = if (response.isEmpty()) null else response.size.plus(page).plus(1)
            val prevKey = if (page == 1) null else response.size.minus(limit)

            Log.d("checkData", "page: $page, response.size: ${response.size}, nextKey: $nextKey, prevKey: $prevKey")

            LoadResult.Page(
                data = response,
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}