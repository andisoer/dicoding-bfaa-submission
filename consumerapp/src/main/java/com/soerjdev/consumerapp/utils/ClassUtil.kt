package com.soerjdev.consumerapp.utils

import android.content.ContentValues
import android.database.Cursor
import com.soerjdev.consumerapp.data.model.FavoriteModel
import java.util.ArrayList

fun ContentValues.toFavoriteModel(): FavoriteModel =
    FavoriteModel(
        avatarUrl = getAsString(COLUMN_avatar_url),
        bio = getAsString(COLUMN_bio),
        blog = getAsString(COLUMN_blog),
        company = getAsString(COLUMN_company),
        createdAt = getAsString(COLUMN_created_at),
        email = getAsString(COLUMN_email),
        eventsUrl = getAsString(COLUMN_events_url),
        followers = getAsInteger(COLUMN_followers),
        followersUrl = getAsString(COLUMN_followers_url),
        following = getAsInteger(COLUMN_following),
        followingUrl = getAsString(COLUMN_following_url),
        gistsUrl = getAsString(COLUMN_gists_url),
        gravatarId = getAsString(COLUMN_gravatar_id),
        hireable = getAsString(COLUMN_hireable),
        htmlUrl = getAsString(COLUMN_html_url),
        id = getAsInteger(COLUMN_id),
        location = getAsString(COLUMN_location),
        login = getAsString(COLUMN_login),
        name = getAsString(COLUMN_name),
        nodeId = getAsString(COLUMN_node_id),
        organizationsUrl = getAsString(COLUMN_organizations_url),
        publicGists = getAsInteger(COLUMN_public_gists),
        publicRepos = getAsInteger(COLUMN_public_repos),
        receivedEventsUrl = getAsString(COLUMN_received_events_url),
        reposUrl = getAsString(COLUMN_repos_url),
        siteAdmin = getAsBoolean(COLUMN_site_admin),
        starredUrl = getAsString(COLUMN_starred_url),
        subscriptionsUrl = getAsString(COLUMN_subscriptions_url),
        twitterUsername = getAsString(COLUMN_twitter_username),
        type = getAsString(COLUMN_type),
        updatedAt = getAsString(COLUMN_updated_at),
        url = getAsString(COLUMN_url)
    )

fun FavoriteModel.toContentValues(): ContentValues =
    ContentValues().apply {
        put(COLUMN_avatar_url, avatarUrl)
        put(COLUMN_bio, bio)
        put(COLUMN_blog, blog)
        put(COLUMN_company, company)
        put(COLUMN_created_at, createdAt)
        put(COLUMN_email, email)
        put(COLUMN_events_url, eventsUrl)
        put(COLUMN_followers, followers)
        put(COLUMN_followers_url, followersUrl)
        put(COLUMN_following, following)
        put(COLUMN_following_url, followingUrl)
        put(COLUMN_gists_url, gistsUrl)
        put(COLUMN_gravatar_id, gravatarId)
        put(COLUMN_hireable, hireable)
        put(COLUMN_html_url, htmlUrl)
        put(COLUMN_id, id)
        put(COLUMN_location, location)
        put(COLUMN_login, login)
        put(COLUMN_name, name)
        put(COLUMN_node_id, nodeId)
        put(COLUMN_organizations_url, organizationsUrl)
        put(COLUMN_public_gists, publicGists)
        put(COLUMN_public_repos, publicRepos)
        put(COLUMN_received_events_url, eventsUrl)
        put(COLUMN_repos_url, reposUrl)
        put(COLUMN_site_admin, siteAdmin)
        put(COLUMN_starred_url, starredUrl)
        put(COLUMN_subscriptions_url, subscriptionsUrl)
        put(COLUMN_twitter_username, twitterUsername)
        put(COLUMN_type, type)
        put(COLUMN_updated_at, updatedAt)
        put(COLUMN_url, url)
    }

fun Cursor.toListFavoriteModel(): ArrayList<FavoriteModel> {
    val listFavorite = ArrayList<FavoriteModel>()

    apply {
        while (moveToNext()){
            listFavorite.add(
                this.toFavoriteModel()
            )
        }
    }

    return listFavorite
}

private fun Cursor.toFavoriteModel(): FavoriteModel =
    FavoriteModel(
        getString(getColumnIndexOrThrow(COLUMN_avatar_url)),
        getString(getColumnIndexOrThrow(COLUMN_bio)),
        getString(getColumnIndexOrThrow(COLUMN_blog)),
        getString(getColumnIndexOrThrow(COLUMN_company)),
        getString(getColumnIndexOrThrow(COLUMN_created_at)),
        getString(getColumnIndexOrThrow(COLUMN_email)),
        getString(getColumnIndexOrThrow(COLUMN_events_url)),
        getInt(getColumnIndexOrThrow(COLUMN_followers)),
        getString(getColumnIndexOrThrow(COLUMN_followers_url)),
        getInt(getColumnIndexOrThrow(COLUMN_following)),
        getString(getColumnIndexOrThrow(COLUMN_following_url)),
        getString(getColumnIndexOrThrow(COLUMN_gists_url)),
        getString(getColumnIndexOrThrow(COLUMN_gravatar_id)),
        getString(getColumnIndexOrThrow(COLUMN_hireable)),
        getString(getColumnIndexOrThrow(COLUMN_html_url)),
        getInt(getColumnIndexOrThrow(COLUMN_id)),
        getString(getColumnIndexOrThrow(COLUMN_location)),
        getString(getColumnIndexOrThrow(COLUMN_login)),
        getString(getColumnIndexOrThrow(COLUMN_name)),
        getString(getColumnIndexOrThrow(COLUMN_node_id)),
        getString(getColumnIndexOrThrow(COLUMN_organizations_url)),
        getInt(getColumnIndexOrThrow(COLUMN_public_gists)),
        getInt(getColumnIndexOrThrow(COLUMN_public_repos)),
        getString(getColumnIndexOrThrow(COLUMN_received_events_url)),
        getString(getColumnIndexOrThrow(COLUMN_repos_url)),
        (getInt(getColumnIndexOrThrow(COLUMN_site_admin)) > 0),
        getString(getColumnIndexOrThrow(COLUMN_starred_url)),
        getString(getColumnIndexOrThrow(COLUMN_subscriptions_url)),
        getString(getColumnIndexOrThrow(COLUMN_twitter_username)),
        getString(getColumnIndexOrThrow(COLUMN_type)),
        getString(getColumnIndexOrThrow(COLUMN_updated_at)),
        getString(getColumnIndexOrThrow(COLUMN_url))
    )
