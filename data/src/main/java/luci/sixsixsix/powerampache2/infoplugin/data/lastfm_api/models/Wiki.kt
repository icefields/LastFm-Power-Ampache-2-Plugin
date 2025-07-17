package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Wiki(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("published")
    val published: String? = null,
    @SerializedName("summary")
    val summary: String? = null
)