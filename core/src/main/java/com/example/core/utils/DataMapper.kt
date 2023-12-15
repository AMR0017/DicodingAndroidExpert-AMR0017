package com.example.core.utils

import com.example.core.data.source.local.entity.UserEntity
import com.example.core.data.source.remote.response.GitDetailResponse
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToDomain(input: List<GitDetailResponse>): Flow<List<User>> {
        val userList = ArrayList<User>()
        input.map {
            val tourism = User(
                it.id,
                it.login,
                it.avatarUrl,
                it.name,
                it.company,
                it.location,
                it.publicRepos,
                it.followers,
                it.following,
                it.url,
            )
            userList.add(tourism)
        }
        return flowOf(userList)
    }

    fun mapResponsesToDetailDomain(input: GitDetailResponse): Flow<User> {
        return flowOf(
            User(
                input.id,
                input.login,
                input.avatarUrl,
                input.name,
                input.company,
                input.location,
                input.publicRepos,
                input.followers,
                input.following,
            )
        )
    }


    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                id = it.id,
                login = it.login,
                avatarUrl = it.imageUrl,
            )
        }

    fun mapDomainToEntity(input: User) = UserEntity(
        id = input.id,
        login = input.login,
        imageUrl = input.avatarUrl
    )
}