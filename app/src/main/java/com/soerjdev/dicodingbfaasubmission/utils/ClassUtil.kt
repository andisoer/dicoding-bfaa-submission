package com.soerjdev.dicodingbfaasubmission.utils

import android.content.ContentValues
import android.database.Cursor
import com.soerjdev.dicodingbfaasubmission.data.database.FavoriteModel
import java.util.*

fun ContentValues.toFavoriteModel(): FavoriteModel =
    FavoriteModel(
        avatar_url = getAsString(COLUMN_avatar_url),
        bio = getAsString(COLUMN_bio),
        blog = getAsString(COLUMN_blog),
        company = getAsString(COLUMN_company),
        created_at = getAsString(COLUMN_created_at),
        email =  getAsString(COLUMN_email),
        events_url = getAsString(COLUMN_events_url),
        followers = getAsInteger(COLUMN_followers),
        followers_url = getAsString(COLUMN_followers_url),
        following = getAsInteger(COLUMN_following),
        following_url = getAsString(COLUMN_following_url),
        gists_url = getAsString(COLUMN_gists_url),
        gravatar_id = getAsString(COLUMN_gravatar_id),
        hireable = getAsString(COLUMN_hireable),
        html_url = getAsString(COLUMN_html_url),
        id = getAsInteger(COLUMN_id),
        location = getAsString(COLUMN_location),
        login = getAsString(COLUMN_login),
        name = getAsString(COLUMN_name),
        node_id = getAsString(COLUMN_node_id),
        organizations_url = getAsString(COLUMN_organizations_url),
        public_gists = getAsInteger(COLUMN_public_gists),
        public_repos = getAsInteger(COLUMN_public_repos) ,
        received_events_url = getAsString(COLUMN_received_events_url),
        repos_url = getAsString(COLUMN_repos_url),
        site_admin = getAsBoolean(COLUMN_site_admin),
        starred_url = getAsString(COLUMN_starred_url),
        subscriptions_url = getAsString(COLUMN_subscriptions_url),
        twitter_username = getAsString(COLUMN_twitter_username),
        type =  getAsString(COLUMN_type),
        updated_at = getAsString(COLUMN_updated_at),
        url = getAsString(COLUMN_url)
    )

fun FavoriteModel.toContentValues(): ContentValues =
    ContentValues().apply {
        put(COLUMN_avatar_url, avatar_url)
        put(COLUMN_bio, bio)
        put(COLUMN_blog, blog)
        put(COLUMN_company, company)
        put(COLUMN_created_at, created_at)
        put(COLUMN_email, email)
        put(COLUMN_events_url, events_url)
        put(COLUMN_followers, followers)
        put(COLUMN_followers_url, followers_url)
        put(COLUMN_following, following)
        put(COLUMN_following_url, following_url)
        put(COLUMN_gists_url, gists_url)
        put(COLUMN_gravatar_id, gravatar_id)
        put(COLUMN_hireable, hireable)
        put(COLUMN_html_url, html_url)
        put(COLUMN_id, id)
        put(COLUMN_location, location)
        put(COLUMN_login, login)
        put(COLUMN_name, name)
        put(COLUMN_node_id, node_id)
        put(COLUMN_organizations_url, organizations_url)
        put(COLUMN_public_gists, public_gists)
        put(COLUMN_public_repos, public_repos)
        put(COLUMN_received_events_url, events_url)
        put(COLUMN_repos_url, repos_url)
        put(COLUMN_site_admin, site_admin)
        put(COLUMN_starred_url, starred_url)
        put(COLUMN_subscriptions_url, subscriptions_url)
        put(COLUMN_twitter_username, twitter_username)
        put(COLUMN_type, type)
        put(COLUMN_updated_at, updated_at)
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

fun Cursor.toFavoriteModel(): FavoriteModel =
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
