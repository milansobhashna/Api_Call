package com.da.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.Observer
import rx.subscriptions.CompositeSubscription


class MainActivity : AppCompatActivity() {

    private var apiManager: ApiManager? = null
    var dataList = arrayListOf<Category>()
    val mCompositeSubscription : CompositeSubscription? = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiManager = ApiManager(this)
        SendData()

    }

    override fun onDestroy() {
        mCompositeSubscription!!.clear()
        super.onDestroy()
    }

    private fun SendData() {

        val login : Observable<CategoryList> = apiManager!!.callLoginApi(10 , 0)

        val subscription = login.subscribe(object : Observer<CategoryList> {
            override fun onError(e: Throwable?) {
            }

            override fun onNext(t: CategoryList?) {
                dataList = t!!.data
                Log.d("testuser", dataList.toString())
                rv_add_new_friend.layoutManager = LinearLayoutManager(this@MainActivity)
                rv_add_new_friend.adapter = AddNewFriendAdapter(
                    this@MainActivity,
                    dataList)
            }

            override fun onCompleted() {

            }

        })

        mCompositeSubscription!!.add(subscription)

    }
}