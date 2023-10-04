package com.example.lab5_pm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5_pm.model.Amiibo
import com.example.lab5_pm.model.Gameseries
import com.example.lab5_pm.model.Post
import com.example.lab5_pm.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponseGS: MutableLiveData<Response<Gameseries>> = MutableLiveData()


    fun getPost(name:String){
        viewModelScope.launch {
            val response = repository.getPost(name)
            myResponse.value = response
        }
    }

    fun getGameseries(){
        viewModelScope.launch {
            val response = repository.getGameseries()
            myResponseGS.value = response
        }
    }
}