package com.abnerescocio.apigithub.di

import com.abnerescocio.apigithub.view.activities.BaseActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(baseActivity: BaseActivity)
}