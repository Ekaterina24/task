package com.example.retrofitwithpost

import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.sql.Time

interface RetroService {

//    @GET("events")
//    @Headers("Accept:application/json", "Content-Type:application/json")
//    suspend fun getUsersList(): Response<List<UserItem>>
    @GET("events")
    suspend fun getUsersList(): Response<List<UserItem>>



    @POST("events")
    suspend fun createUser(@Body params: ResponseBody?): Response<ResponseItem>





    @DELETE("events/{event_id}")
    suspend fun deleteUser(@Path("event_id") event_id: String): Response<UserItem>

    @GET("events/{event_id}")
    suspend fun getUser(@Path("event_id") event_id: String): Response<UserItem>

    @PATCH("events/{event_id}")
    suspend fun updateUser(@Path("event_id") event_id: String, @Body params: UserItem): Response<UserItem>

//    @GET("users")
//    @Headers("Accept:application/json", "Content-Type:application/json")
//    suspend fun searchUsers(@Query("name") searchText: String): Response<List<UserItem>>

//    @POST("users")
//    @Headers("Accept:application/json", "Content-Type:application/json", "Authorization: Bearer 853e0adda85ad000ca2f37887fab58ae64fe1441997e36a1a7f4b0f85b48012c")
//    suspend fun createUser(@Body params: UserItem): Response<UserItem>



}