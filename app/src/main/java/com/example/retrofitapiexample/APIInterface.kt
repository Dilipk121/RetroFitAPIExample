package com.example.retrofitapiexample

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("products") //mention here... end point/line of API
    fun getProductData() : Call<MyData>

}