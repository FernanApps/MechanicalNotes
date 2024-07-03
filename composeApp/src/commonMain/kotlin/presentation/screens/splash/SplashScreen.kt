package presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import mechanicalnotes.composeapp.generated.resources.Res
import mechanicalnotes.composeapp.generated.resources.app_name
import mechanicalnotes.composeapp.generated.resources.compose_multiplatform
import mechanicalnotes.composeapp.generated.resources.foreground
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun SplashScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit) {

    val progress = remember { Animatable(0f) }

    LaunchedEffect(true) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 4500)
        )
        onNavigate()
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(Res.drawable.foreground),
            contentDescription = "",
            modifier = Modifier.size(150.dp),
        )
        Text(stringResource(Res.string.app_name))
        Spacer(Modifier.weight(1f))
        LinearProgressIndicator(
            progress = { progress.value },
            modifier = Modifier.fillMaxWidth()
        )
    }
}