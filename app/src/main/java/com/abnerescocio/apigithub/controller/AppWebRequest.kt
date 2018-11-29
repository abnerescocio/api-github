package com.abnerescocio.apigithub.controller

import com.abnerescocio.apigithub.model.QueryUsers
import com.abnerescocio.apigithub.model.Repo
import com.abnerescocio.apigithub.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppWebRequest {

    private var service: GitHubService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_API_GITHUB)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(GitHubService::class.java)
    }

    fun listUsers(): Call<List<User>> {
        return service.listUsers()
    }

    fun listUsers(query: String): Call<QueryUsers> {
        return service.listUsers(query)
    }

    fun getUser(user: String) : Call<User> {
        return service.getUser(user)
    }

    fun listRepo(user: String) : Call<List<Repo>> {
        return service.listRepo(user)
    }

    companion object {
        const val URL_API_GITHUB = "https://api.github.com/"
    }
}