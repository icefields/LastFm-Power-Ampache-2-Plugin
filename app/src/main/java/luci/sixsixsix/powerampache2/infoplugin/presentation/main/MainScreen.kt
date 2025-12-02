package luci.sixsixsix.powerampache2.infoplugin.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import luci.sixsixsix.powerampache2.infoplugin.R
import luci.sixsixsix.powerampache2.infoplugin.presentation.main.components.ChangeTokenView
import luci.sixsixsix.powerampache2.infoplugin.presentation.main.components.ClearDbButton
import luci.sixsixsix.powerampache2.infoplugin.presentation.main.components.MainTopBar

val mainFontSize = 16.sp
val smallFontSize = 12.sp
val screenPadding
    @Composable get() = dimensionResource(R.dimen.screen_padding)


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)
    val token = mainScreenViewModel.tokenStateFlow.collectAsStateWithLifecycle()
    MainScreenContent(
        modifier = modifier,
        token = token.value,
        onTokenChange = mainScreenViewModel::setToken,
        onClearLyrics = mainScreenViewModel::clearStoredLyrics,
        onFetchLyricsDebug = mainScreenViewModel::fetchLyricsDebug,
        onBack = onBack
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    token: String,
    onTokenChange: (String) -> Unit,
    onClearLyrics: () -> Unit,
    onFetchLyricsDebug: () -> Unit = { },
    onBack: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = { MainTopBar(Modifier.fillMaxWidth(), onBack = onBack) }
    ) {
        Box(Modifier.fillMaxSize().padding(it)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = screenPadding).padding(horizontal = screenPadding)

            ) {
                ChangeTokenView(Modifier.fillMaxWidth(), token = token, onTokenChange)
                Divider(Modifier.fillMaxWidth().padding(vertical = screenPadding))
                ClearDbButton(Modifier.fillMaxWidth(), onClearLyrics)
                Divider(Modifier.fillMaxWidth().padding(vertical = screenPadding))
                // FetchLyricsDebugButton(onFetchLyricsDebug)
            }
        }
    }
}

@Composable
private fun FetchLyricsDebugButton(onFetchLyricsDebug: () -> Unit) {
    Button(
        onClick = onFetchLyricsDebug
    ) { Text("Fetch Lyrics Test", textAlign = TextAlign.Center) }
}

@Preview
@Composable
fun previewMainScreen(){
    MainScreenContent(
        token = "abcde666", onTokenChange =  { }, onClearLyrics =  { }, onBack = { } )
}
