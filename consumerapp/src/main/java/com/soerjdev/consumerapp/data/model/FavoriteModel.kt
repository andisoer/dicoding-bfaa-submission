package com.soerjdev.consumerapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
class FavoriteModel (
     val avatarUrl: String?,
     val bio: String?,
     val blog: String?,
     val company: String?,
     val createdAt: String?,
     val email: String?,
     val eventsUrl: String?,
     val followers: Int?,
     val followersUrl: String?,
     val following: Int?,
     val followingUrl: String?,
     val gistsUrl: String?,
     val gravatarId: String?,
     val hireable: String?,
     val htmlUrl: String?,
     val id: Int,
     val location: String?,
     val login: String?,
     val name: String?,
     val nodeId: String?,
     val organizationsUrl: String?,
     val publicGists: Int?,
     val publicRepos: Int?,
     val receivedEventsUrl: String?,
     val reposUrl: String?,
     val siteAdmin: Boolean?,
     val starredUrl: String?,
     val subscriptionsUrl: String?,
     val twitterUsername: String?,
     val type: String?,
     val updatedAt: String?,
     val url: String?
): Parcelable