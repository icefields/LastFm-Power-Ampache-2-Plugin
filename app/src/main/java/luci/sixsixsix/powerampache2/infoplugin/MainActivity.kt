package luci.sixsixsix.powerampache2.infoplugin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import luci.sixsixsix.powerampache2.infoplugin.presentation.main.MainScreen
import luci.sixsixsix.powerampache2.infoplugin.presentation.theme.PowerAmpachePluginTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PowerAmpachePluginTheme(darkTheme = true, dynamicColor = true) {
                MainScreen()
            }
        }
    }
}
