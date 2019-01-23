package com.abnerescocio.apigithub.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.abnerescocio.apigithub.api.GitHubService
import com.abnerescocio.apigithub.di.DaggerAppComponent
import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    @Inject
    protected lateinit var service: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)
    }
}