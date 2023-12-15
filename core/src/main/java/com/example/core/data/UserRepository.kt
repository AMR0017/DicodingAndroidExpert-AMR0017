package com.example.core.data

import androidx.lifecycle.LiveData
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.GitDetailResponse
import com.example.core.domain.model.User
import com.example.core.domain.repository.IUserRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository{
    override fun getUser(): Flow<Resource<List<User>>> =
        object : NetworkResource<List<User>, List<GitDetailResponse>>() {
            override fun loadFromNet(data: List<GitDetailResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GitDetailResponse>>> {
                return remoteDataSource.getAllUserGit()
            }
        }.asFlow()

    override fun searchUser(username: String): Flow<Resource<List<User>>> =
        object : NetworkResource<List<User>, List<GitDetailResponse>>() {
            override fun loadFromNet(data: List<GitDetailResponse>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GitDetailResponse>>> {
                return if (username.isEmpty()) {
                    remoteDataSource.getAllUserGit()
                } else {
                    remoteDataSource.searchUser(username)
                }
            }
        }.asFlow()



    override fun getDetailUser(username: String): Flow<Resource<User>> {
        return object : NetworkResource<User, GitDetailResponse>() {
            override fun loadFromNet(data: GitDetailResponse): Flow<User> {
                return DataMapper.mapResponsesToDetailDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<GitDetailResponse>> {
                return remoteDataSource.getDetailUser(username)
            }

        }.asFlow()
    }

    override fun getFavoriteUser(): Flow<List<User>> {
        return localDataSource.getAllGitUser().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGitUser(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute{
            localDataSource.setFavorite(userEntity, state)
        }
    }

    override fun checkFav(username: String): LiveData<Boolean>{
        return localDataSource.isFavorite(username)
    }
}