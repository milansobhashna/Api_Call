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

    var dataList = arrayListOf<Category>()

    val api = ApiConfig("http://ayurvedchikitsa.xperttech.in/")
    val mCompositeSubscription: CompositeSubscription? = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SendData()

    }

    override fun onDestroy() {
        mCompositeSubscription!!.clear()
        super.onDestroy()
    }

    private fun SendData() {

        val call = applySchedulers(this@MainActivity)
        val apiObservable: Observable<CategoryList> =
            api.getClientWithHeader("token")!!.create(API::class.java).categoryApi(10, 0)
        val loginchk: Observable<CategoryList> =
            call.call(apiObservable).compose(call.applySchedulers())

        val subscription = loginchk.subscribe(object : Observer<CategoryList> {
            override fun onError(e: Throwable?) {
            }

            override fun onNext(t: CategoryList?) {
                dataList = t!!.data
                Log.d("testuser", dataList.toString())
                rv_add_new_friend.layoutManager = LinearLayoutManager(this@MainActivity)
                rv_add_new_friend.adapter = AddNewFriendAdapter(
                    this@MainActivity,
                    dataList
                )
            }

            override fun onCompleted() {

            }

        })

        mCompositeSubscription!!.add(subscription)

    }
}