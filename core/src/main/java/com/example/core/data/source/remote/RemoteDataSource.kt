package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.GitDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService : ApiService) {
    suspend fun getAllUserGit(): Flow<ApiResponse<List<GitDetailResponse>>> {
        return flow {
            try {
                val response = apiService.getGitUserList()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Error in getAllUserGit: ${e.message}", e)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchUser(q : String): Flow<ApiResponse<List<GitDetailResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.searchUser(q).items
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailUser(username: String): Flow<ApiResponse<GitDetailResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.detailUsers(username)
                emit(ApiResponse.Success(response))

            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollowersList(username: String): Flow<ApiResponse<List<GitDetailResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getFollowers(username)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollowingList(username: String): Flow<ApiResponse<List<GitDetailResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getFollowing(username)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}