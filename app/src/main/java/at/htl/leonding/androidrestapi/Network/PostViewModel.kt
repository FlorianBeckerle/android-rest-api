package at.htl.leonding.androidrestapi.Network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.leonding.androidrestapi.Model.Post
import kotlinx.coroutines.launch

enum class PostAPIStatus { LOADING, DONE, ERROR }

class PostViewModel : ViewModel() {


    private val _status = MutableLiveData<PostAPIStatus>()

    val status: LiveData<PostAPIStatus>
        get() = _status

    private val _posts = MutableLiveData<List<Post>>()

    val posts: LiveData<List<Post>>
        get() = _posts

    init {
        getPosts()
    }

    private fun getPosts() {
        _status.value = PostAPIStatus.LOADING

        viewModelScope.launch {
            try {
                _posts.value = PostApi.retrofitService.getPosts()
                _status.value = PostAPIStatus.DONE
            } catch (e: Exception) {
                Log.d("postViewModel: ", e.toString())
                _status.value = PostAPIStatus.ERROR
            }

        }
    }
}