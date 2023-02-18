package ru.mvlikhachev.restapiapp.presentation.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.mvlikhachev.restapiapp.data.api.model.PostResponse
import ru.mvlikhachev.restapiapp.domain.usecases.DeletePostUseCase
import ru.mvlikhachev.restapiapp.domain.usecases.GetAllPostsUseCase
import ru.mvlikhachev.restapiapp.domain.usecases.PatchPostUseCase
import ru.mvlikhachev.restapiapp.domain.usecases.PostPostUseCase
import ru.mvlikhachev.restapiapp.domain.usecases.PutPostUseCase
import ru.mvlikhachev.restapiapp.utils.NetworkResult
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val deletePostUseCase: DeletePostUseCase,
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val patchPostUseCase: PatchPostUseCase,
    private val postPostUseCase: PostPostUseCase,
    private val putPostUseCase: PutPostUseCase
): ViewModel() {
    private val _allPostsResponse = MutableLiveData<NetworkResult<List<PostResponse>>>()
    val allPostResponse: LiveData<NetworkResult<List<PostResponse>>>
        get() = _allPostsResponse

    init {
        getAllPosts()
    }

    fun getPagingAllPosts() = getAllPostsUseCase.invokePaging()

    private fun getAllPosts() {
        viewModelScope.launch {
            getAllPostsUseCase.invoke().let {
                _allPostsResponse.value = it
            }
        }
    }

    fun postPosts() {
        viewModelScope.launch {
            postPostUseCase.invoke(body = PostResponse(title = "Test title", body = "Test body"))
        }
    }

    fun putPost() {
        viewModelScope.launch {
            putPostUseCase.invoke(id = "1", body = PostResponse(title = "Test title", body = "Test body")).let {
                Log.d("checkData", "data: ${it.data}")
            }
        }
    }

    fun patchPost() {
        viewModelScope.launch {
            patchPostUseCase.invoke(id = "1", body = PostResponse(title = "Test title", body = "Test body")).let {
                Log.d("checkData", "data: ${it.data}")
            }
        }
    }

    fun deletePost() {
        viewModelScope.launch {
            deletePostUseCase.invoke(id = "1").let {
                Log.d("checkData", "data: ${it.data}")
            }
        }
    }
}