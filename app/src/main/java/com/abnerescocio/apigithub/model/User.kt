package com.abnerescocio.apigithub.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User: Serializable {

    @SerializedName("login")
    var name: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("site_admin")
    var isAdmin: Boolean = false

    @SerializedName("company")
    var company: String? = null

    @SerializedName("location")
    var location: String? = null

    @SerializedName("email")
    var email: String? = null
}