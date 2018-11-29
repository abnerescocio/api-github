package com.abnerescocio.apigithub.controller

import com.abnerescocio.apigithub.model.QueryUsers
import com.abnerescocio.apigithub.model.Repo
import com.abnerescocio.apigithub.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("users")
    fun listUsers() : Call<List<User>>

    @GET("search/users")
    fun listUsers(@Query("q") query: String) : Call<QueryUsers>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String) : Call<User>

    @GET("users/{user}/repos")
    fun listRepo(@Path("user") user: String) : Call<List<Repo>>
}