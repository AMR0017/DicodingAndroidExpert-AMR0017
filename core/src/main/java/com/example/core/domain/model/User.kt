package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int,
    var login:String,
    val avatarUrl: String,
    val name: String? = null,
    val company: String? = null,
    val location: String? = null,
    val publicRepos: Int? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val url: String? = null,
    val html_url: String? = null,
//    val isFavorite: Boolean
) : Parcelable