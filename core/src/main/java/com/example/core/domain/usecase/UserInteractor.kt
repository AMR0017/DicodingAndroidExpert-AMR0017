package com.example.core.domain.usecase

import com.example.core.domain.model.User
import com.example.core.domain.repository.IUserRepository

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {
    override fun getUser() = userRepository.getUser()
    override fun searchUser(username: String) = userRepository.searchUser(username)
    override fun getDetailUser(username: String)= userRepository.getDetailUser(username)
    override fun getFavoriteUser() = userRepository.getFavoriteUser()
    override fun setFavoriteUser(user: User, state: Boolean) = userRepository.setFavoriteGitUser(user,state)
    override fun checkFav(username: String) = userRepository.checkFav(username)
}