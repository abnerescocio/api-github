package com.abnerescocio.apigithub.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.abnerescocio.apigithub.R
import com.abnerescocio.apigithub.controller.AppWebRequest
import com.abnerescocio.apigithub.model.QueryUsers
import com.abnerescocio.apigithub.model.User
import com.abnerescocio.apigithub.view.adapters.UsersAdapter

import kotlinx.android.synthetic.main.activity_search_user.*
import kotlinx.android.synthetic.main.content_search_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserActivity : AppCompatActivity(), SearchView.OnQueryTextListener, UsersAdapter.OnListInteraction {

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu.findItem(R.id.action_search)?.actionView as SearchView
        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        progress.visibility = View.VISIBLE
        searchView?.clearFocus()
        val request = AppWebRequest().listUsers(query ?: "")
        request.enqueue(object : Callback<QueryUsers> {
            override fun onFailure(call: Call<QueryUsers>, t: Throwable) {
                progress.visibility = View.GONE
            }
            override fun onResponse(call: Call<QueryUsers>, response: Response<QueryUsers>) {
                progress.visibility = View.GONE
                users.adapter = UsersAdapter(this@SearchUserActivity, response.body()?.users)
            }
        })
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }

    override fun onItemClick(user: User?) {

    }
}
