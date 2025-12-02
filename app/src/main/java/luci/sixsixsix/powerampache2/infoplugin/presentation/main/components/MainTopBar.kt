package luci.sixsixsix.powerampache2.infoplugin.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import luci.sixsixsix.powerampache2.infoplugin.R

//@Composable
//fun MainTopBarOld(modifier: Modifier = Modifier) {
//    Column(modifier) {
//        Spacer(Modifier
//            .fillMaxWidth()
//            .height(WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
//            .background(MaterialTheme.colors.onBackground)
//        )
//
//        Spacer(Modifier.Companion.height(screenPadding))
//        Text(
//            text = stringResource(R.string.app_name_topBar),
//            fontSize = 20.sp,
//            fontStyle = FontStyle.Normal,
//            fontWeight = FontWeight.Bold,
//            color = MaterialTheme.colors.onPrimary,
//            modifier = Modifier.fillMaxWidth().padding(horizontal = screenPadding),
//            textAlign = TextAlign.Start
//        )
//        Spacer(Modifier.Companion.height(screenPadding))
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(modifier: Modifier = Modifier, onBack: () -> Unit) {
    TopAppBar(
        modifier = modifier.background(MaterialTheme.colorScheme.primary).statusBarsPadding(),
        title = { Text(
            text = stringResource(R.string.app_name_topBar),
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        navigationIcon = { IconButton(onClick = onBack) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back") }
        },
    )
}