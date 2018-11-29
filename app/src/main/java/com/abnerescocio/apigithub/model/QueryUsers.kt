package com.abnerescocio.apigithub.model

import com.google.gson.annotations.SerializedName

class QueryUsers {
    @SerializedName("total_count")
    var totalCount : Int? = null

    @SerializedName("items")
    var users : MutableList<User>? = null
}