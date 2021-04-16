package com.example.botnav.service

import retrofit2.Call
import com.example.botnav.ui.model.Comment
import retrofit2.http.GET

interface CommentApi {
    @GET("comments")
    fun getComments(): Call<List<Comment>>

}