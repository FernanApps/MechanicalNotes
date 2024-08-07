import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.materialkolor.PaletteStyle
import com.materialkolor.rememberDynamicColorScheme
import di.appModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import presentation.NoteViewModel
import presentation.Screens
import presentation.screens.details.DetailScreen
import presentation.screens.details.bundleKeyNoteId
import presentation.screens.home.HomeScreen
import presentation.screens.splash.SplashScreen


internal val seedColor = Color(0xff303F9F)

@Composable
@Preview
fun App() {

    KoinApplication(application = {
        modules(appModule())
    }) {

        val colorScheme = rememberDynamicColorScheme(seedColor, false, style = PaletteStyle.Vibrant)


        MaterialTheme(colorScheme = colorScheme) {

            val navController: NavHostController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            /*
            val currentScreen = Screens.valueOf(
                backStackEntry?.destination?.route ?: Screens.Splash()
            )

             */

            val viewModel: NoteViewModel = koinViewModel<NoteViewModel>()

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
                    HomeScreen(onNavigateDetails = { id ->
                        navController.navigate(Screens.Details.withArgs(id))
                    }, viewModel)
                }

                composable(route = Screens.Details()) { it ->
                    val id = it.arguments!!.getString(bundleKeyNoteId)!!.toLong()
                    DetailScreen(id, onBack = {
                        navController.popBackStack()
                    }, viewModel)
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


