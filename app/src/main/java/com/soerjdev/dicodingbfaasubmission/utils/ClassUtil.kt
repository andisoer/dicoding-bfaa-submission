package com.soerjdev.dicodingbfaasubmission.utils

import android.content.ContentValues
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel

fun ContentValues.toFavoriteModel(): FavoriteModel =
    FavoriteModel(
        avatarUrl = getAsString(COLUMN_avatar_url),
        bio = getAsString(COLUMN_bio),
        blog = getAsString(COLUMN_blog),
        company = getAsString(COLUMN_company),
        createdAt = getAsString(COLUMN_created_at),
        email =  getAsString(COLUMN_email),
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
        publicRepos = getAsInteger(COLUMN_public_repos) ,
        receivedEventsUrl = getAsString(COLUMN_received_events_url),
        reposUrl = getAsString(COLUMN_repos_url),
        siteAdmin = getAsBoolean(COLUMN_site_admin),
        starredUrl = getAsString(COLUMN_starred_url),
        subscriptionsUrl = getAsString(COLUMN_subscriptions_url),
        twitterUsername = getAsString(COLUMN_twitter_username),
        type =  getAsString(COLUMN_type),
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