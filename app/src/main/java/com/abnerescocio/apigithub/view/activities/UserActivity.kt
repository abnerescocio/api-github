package com.abnerescocio.apigithub.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.abnerescocio.apigithub.R
import com.abnerescocio.apigithub.controller.AppWebRequest
import com.abnerescocio.apigithub.model.Repo
import com.abnerescocio.apigithub.model.User
import com.abnerescocio.apigithub.view.adapters.RepositoriesAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.content_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)

        val args = intent?.extras
        if (args != null) {
            var user = args.getSerializable(USER) as User?
            title = user?.name
            Glide.with(container).load(user?.avatarUrl).into(avatar)

            val requestUser = AppWebRequest().getUser(user?.name ?: "")
            requestUser.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    progress.visibility = View.GONE
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    progress.visibility = View.GONE
                    user = response.body()
                }
            })

            val requestUserRepos = AppWebRequest().listRepo(user?.name ?: "")
            requestUserRepos.enqueue(object : Callback<List<Repo>> {
                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                }
                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    repos.adapter = RepositoriesAdapter(response.body())
                }
            })
        }
    }

    companion object {
        const val USER = "userName"
    }
}
