package com.soerjdev.consumerapp

const val AUTH_TOKEN = "15f44dbc4aca6258a2ed889264fb79a5bd02df92"

const val BASE_URL = "https://api.github.com/"

const val TAG_SHARED_PREF = "SharedPrefApps"
const val TAG_IS_ALARM_ENABLED = "isAlarmEnabled"


//URI for ContentProvider
const val URI_AUTHORITY = "com.soerjdev.dicodingbfaasubmission"
const val SCHEME = "content"
//Database
const val FAVORITE_TABLE_NAME = "favorites_table"
const val FAVORITE_URI = "$SCHEME://$URI_AUTHORITY/$FAVORITE_TABLE_NAME"

//favorite_users columns
const val COLUMN_avatar_url = "avatar_url"
const val COLUMN_bio = "bio"
const val COLUMN_blog = "blog"
const val COLUMN_company = "company"
const val COLUMN_created_at = "created_at"
const val COLUMN_email = "email"
const val COLUMN_events_url = "events_url"
const val COLUMN_followers = "followers"
const val COLUMN_followers_url = "followers_url"
const val COLUMN_following = "following"
const val COLUMN_following_url = "following_url"
const val COLUMN_gists_url = "gists_url"
const val COLUMN_gravatar_id = "gravatar_id"
const val COLUMN_hireable = "hireable"
const val COLUMN_html_url = "html_url"
const val COLUMN_id = "id"
const val COLUMN_location = "location"
const val COLUMN_login = "login"
const val COLUMN_name = "name"
const val COLUMN_node_id = "node_id"
const val COLUMN_organizations_url = "organizations_url"
const val COLUMN_public_gists = "public_gists"
const val COLUMN_public_repos = "public_repos"
const val COLUMN_received_events_url = "received_events_url"
const val COLUMN_repos_url = "repos_url"
const val COLUMN_site_admin = "site_admin"
const val COLUMN_starred_url = "starred_url"
const val COLUMN_subscriptions_url = "subscriptions_url"
const val COLUMN_twitter_username = "twitter_username"
const val COLUMN_type = "type"
const val COLUMN_updated_at = "updated_at"
const val COLUMN_url = "url"