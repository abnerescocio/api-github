package com.abnerescocio.apigithub.view.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val args = intent?.extras
        if (args != null) {
            var user = args.getSerializable(USER) as User?
            title = user?.name
            Glide.with(container).load(user?.avatarUrl).into(avatar)

            val requestUser = AppWebRequest().getUser(user?.name ?: "")
            requestUser.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Snackbar.make(repos, t.localizedMessage, Snackbar.LENGTH_INDEFINITE).setAction(R.string.try_again) {
                        call.enqueue(this)
                    }.show()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    user = response.body()
                    user?.company?.let { organization.text = it }
                    user?.location?.let { location.text = it }
                }
            })

            val requestUserRepos = AppWebRequest().listRepo(user?.name ?: "")
            requestUserRepos.enqueue(object : Callback<List<Repo>> {
                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                    progress.visibility = View.GONE
                    Snackbar.make(repos, t.localizedMessage, Snackbar.LENGTH_INDEFINITE).setAction(R.string.try_again) {
                        call.enqueue(this)
                    }.show()
                }
                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    progress.visibility = View.GONE
                    repos.adapter = RepositoriesAdapter(response.body())
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return true
    }

    companion object {
        const val USER = "userName"
    }
}
