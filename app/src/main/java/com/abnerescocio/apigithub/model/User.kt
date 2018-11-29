package com.abnerescocio.apigithub.model

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("login")
    var name: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("site_admin")
    var isAdmin: Boolean = false
}