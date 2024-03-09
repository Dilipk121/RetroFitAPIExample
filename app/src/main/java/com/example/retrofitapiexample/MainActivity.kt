package com.example.retrofitapiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyProductAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)
        myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        val retroFitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)

        val retroFitData = retroFitBuilder.getProductData()

        //get below code : ctrl+shift+space
        retroFitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if API call is success, then use the data of API and show in your app
                val responseBody = response.body()
                val productList: List<Product> = responseBody?.products!!  // this is the list of products


                myAdapter = MyProductAdapter(this@MainActivity,productList)

                myRecyclerView.adapter = myAdapter

                //its just for testing purpose of API
//                val test = StringBuilder()
//                if (productList != null) {
//                    for ( i in productList){
//
//                        test.append(i.title + " ")
//                    }
//                }
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if API call fails
                Log.d("MAIN","onFailure"+t.message)
            }
        })

    }

}