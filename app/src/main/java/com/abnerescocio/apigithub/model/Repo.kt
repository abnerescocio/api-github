package com.abnerescocio.apigithub.model

import com.google.gson.annotations.SerializedName

class Repo {
    var name: String? = null

    @SerializedName("watchers_count")
    var watchersCount: Int? = null

    @SerializedName("stargazers_count")
    var stargazersCount: Int? = null

    @SerializedName("forks_count")
    var forksCount: Int? = null
}