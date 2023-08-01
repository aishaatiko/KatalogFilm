package com.alicea.katalogfilm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result>>()
    fun getPopularMovies() {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNjkyOTg4NzhkNWVhODI0NjUzODgxNzU2NjM4NjNlNiIsInN1YiI6IjVjNDY4NjA2MGUwYTI2NDk0ZGM4ODQ2OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.EchfjPLbZS8Ff8GKw6vk92DybkNGIUQqywPUGpaIKCg"
        RetrofitInstance.api.getPopularMovies("Bearer $token")
            .enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.body()!=null) {
                        movieLiveData.value = response.body()!!.results
                    } else {
                        return
                    }
                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }

            })
    }
    fun observeMovieLiveData() : LiveData<List<Result>> {
        return movieLiveData
    }
}