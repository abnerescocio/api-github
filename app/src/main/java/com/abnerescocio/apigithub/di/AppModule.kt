package com.abnerescocio.apigithub.di

import com.abnerescocio.apigithub.api.GitHubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    fun providerService(): GitHubService = Retrofit.Builder()
        .baseUrl(URL_API_GITHUB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubService::class.java)

    companion object {
        const val URL_API_GITHUB = "https://api.github.com/"
    }
}