package com.abnerescocio.apigithub.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.abnerescocio.apigithub.R
import com.abnerescocio.apigithub.model.User
import com.abnerescocio.apigithub.view.adapters.UsersAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity(), UsersAdapter.OnListInteraction {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        requestUsers()

        swipe.setOnRefreshListener { requestUsers() }
    }

    private fun requestUsers() {
        val request = service.listUsers()
        request.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                progress.visibility = View.GONE
                swipe.isRefreshing = false
                Snackbar.make(users, R.string.list_users_error, Snackbar.LENGTH_INDEFINITE).setAction(R.string.try_again) {
                    progress.visibility = View.VISIBLE
                    call.clone().enqueue(this)
                }.show()
            }
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                progress.visibility = View.GONE
                swipe.isRefreshing = false
                users.adapter = UsersAdapter(this@MainActivity, response.body())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                startActivity(Intent(this, SearchUserActivity::class.java))
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(user: User?) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra(UserActivity.USER_NAME, user)
        startActivity(intent)
    }
}
