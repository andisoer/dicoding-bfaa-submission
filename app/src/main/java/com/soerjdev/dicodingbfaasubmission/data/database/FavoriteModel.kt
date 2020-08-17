package com.soerjdev.dicodingbfaasubmission.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
class FavoriteModel (
    @ColumnInfo(name ="avatar_url") val avatarUrl: String?,
    @ColumnInfo(name ="bio") val bio: String?,
    @ColumnInfo(name ="blog") val blog: String?,
    @ColumnInfo(name ="company") val company: String?,
    @ColumnInfo(name ="created_at") val createdAt: String?,
    @ColumnInfo(name ="email") val email: String?,
    @ColumnInfo(name ="events_url") val eventsUrl: String?,
    @ColumnInfo(name ="followers") val followers: Int?,
    @ColumnInfo(name ="followers_url") val followersUrl: String?,
    @ColumnInfo(name ="following") val following: Int?,
    @ColumnInfo(name ="following_url") val followingUrl: String?,
    @ColumnInfo(name ="gists_url") val gistsUrl: String?,
    @ColumnInfo(name ="gravatar_id") val gravatarId: String?,
    @ColumnInfo(name ="hireable") val hireable: String?,
    @ColumnInfo(name ="html_url") val htmlUrl: String?,
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name ="id") val id: Int,
    @ColumnInfo(name ="location") val location: String?,
    @ColumnInfo(name ="login") val login: String?,
    @ColumnInfo(name ="name") val name: String?,
    @ColumnInfo(name ="node_id") val nodeId: String?,
    @ColumnInfo(name ="organizations_url") val organizationsUrl: String?,
    @ColumnInfo(name ="public_gists") val publicGists: Int?,
    @ColumnInfo(name ="public_repos") val publicRepos: Int?,
    @ColumnInfo(name ="received_events_url") val receivedEventsUrl: String?,
    @ColumnInfo(name ="repos_url") val reposUrl: String?,
    @ColumnInfo(name ="site_admin") val siteAdmin: Boolean?,
    @ColumnInfo(name ="starred_url") val starredUrl: String?,
    @ColumnInfo(name ="subscriptions_url") val subscriptionsUrl: String?,
    @ColumnInfo(name ="twitter_username") val twitterUsername: String?,
    @ColumnInfo(name ="type") val type: String?,
    @ColumnInfo(name ="updated_at") val updatedAt: String?,
    @ColumnInfo(name ="url") val url: String?
)