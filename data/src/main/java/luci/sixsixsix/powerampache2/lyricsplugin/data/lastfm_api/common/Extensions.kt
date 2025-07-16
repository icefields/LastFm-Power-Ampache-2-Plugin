package luci.sixsixsix.powerampache2.lyricsplugin.data.lastfm_api.common

import org.json.JSONArray

const val EMPTY_TAGS = "[]"

fun String.removeHtmlAnchor() = this.replace(Regex("<a.*?</a>"), "\n\n")

fun parseTags(tagsStr: String): List<String> {
    if (tagsStr.isBlank()) return listOf()
    return try {
        JSONArray(tagsStr).let { jsonArray ->
            List(jsonArray.length()) { i -> jsonArray.getString(i) }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        listOf()
    }
}

fun tagsToString(tags: List<String>): String = JSONArray(tags).toString()
