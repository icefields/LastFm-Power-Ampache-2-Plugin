package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.common

import luci.sixsixsix.powerampache2.infoplugin.data.BuildConfig

const val BASE_URL = "https://ws.audioscrobbler.com/"
const val LASTFM_API_KEY = BuildConfig.LASTFM_API_KEY
const val LASTFM_SECRET_KEY = BuildConfig.LASTFM_SECRET_KEY
const val TIMEOUT_CONNECTION_S = 15L
const val TIMEOUT_READ_S = 60L
const val TIMEOUT_WRITE_S = 60L
const val POSITION_NOT_VALID = -1
const val DURATION_NOT_VALID = -1
const val RANK_NOT_VALID = -1
const val DESCRIPTION_NOT_VALID = ""
