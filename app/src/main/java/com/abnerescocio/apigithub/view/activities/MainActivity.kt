package com.abnerescocio.apigithub.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.abnerescocio.apigithub.R
import com.abnerescocio.apigithub.controller.AppWebRequest
import com.abnerescocio.apigithub.model.User
import com.abnerescocio.apigithub.view.adapters.UsersAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        requestUsers()

        swipe.setOnRefreshListener { requestUsers() }
    }

    private fun requestUsers() {
        val request = AppWebRequest().listUsers()
        request.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                progress.visibility = View.GONE
                swipe.isRefreshing = false
            }
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                progress.visibility = View.GONE
                swipe.isRefreshing = false
                users.adapter = UsersAdapter(response.body())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
