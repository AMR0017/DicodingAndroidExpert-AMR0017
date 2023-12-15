package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.GitDetailResponse
import com.example.core.data.source.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getGitUserList(): List<GitDetailResponse>

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String
    ) : SearchResponse

    @GET("users/{username}")
    suspend fun detailUsers(
        @Path("username") username : String
    ): GitDetailResponse

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String
    ): List<GitDetailResponse>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String
    ): List<GitDetailResponse>
}