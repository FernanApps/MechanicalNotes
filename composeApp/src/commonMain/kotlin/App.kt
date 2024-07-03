import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import di.appModule
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import mechanicalnotes.composeapp.generated.resources.Res
import mechanicalnotes.composeapp.generated.resources.compose_multiplatform
import org.koin.compose.KoinApplication
import platform.getFirebaseManager
import presentation.Screens
import presentation.home.HomeScreen
import presentation.screens.splash.SplashScreen


@Composable
@Preview
fun App() {

    KoinApplication(application = {
        modules(appModule())
    }) {

        MaterialTheme {

            val navController: NavHostController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentScreen = Screens.valueOf(
                backStackEntry?.destination?.route ?: Screens.Splash()
            )


            NavHost(
                navController = navController,
                startDestination = Screens.Splash(),
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = Screens.Splash()) {

                    SplashScreen(
                        onNavigate = {
                            navController.navigate(Screens.Home())
                        }
                    )
                }
                composable(route = Screens.Home()) {
                    HomeScreen()
                }
            }
            /*
            var showContent by remember { mutableStateOf(false) }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }

             */
        }
    }
}

