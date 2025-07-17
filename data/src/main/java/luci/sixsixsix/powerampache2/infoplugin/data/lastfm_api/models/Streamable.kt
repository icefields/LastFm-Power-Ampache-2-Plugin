package luci.sixsixsix.powerampache2.infoplugin.data.lastfm_api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Streamable(
    @SerializedName("fulltrack")
    val fulltrack: String? = null,
    @SerializedName("#text")
    val text: String? = null
)