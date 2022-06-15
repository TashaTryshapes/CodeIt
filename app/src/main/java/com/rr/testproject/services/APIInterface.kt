package com.rr.testproject.services

import com.rr.testproject.data.UserData
import com.rr.testproject.data.UserIdData.UserIdData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("/posts")
    suspend fun getUsersList() : Response<List<UserData>>

    @GET("/users/{userId}")
    suspend fun userData(@Path("userId") language : String) : Response<UserIdData>
}