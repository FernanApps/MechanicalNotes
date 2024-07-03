package presentation;

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource

enum class Screens(
    private val args: List<String>? = null,
    val title: StringResource? = null,
    val isTabItem: Boolean = false,
    val unselectedIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null,
) {
    Splash,
    Home;

    operator fun invoke(): String {
        val argList = StringBuilder()
        args?.let { nnArgs ->
            nnArgs.forEach { arg -> argList.append("/{$arg}") }
        }
        return name + argList
    }


    fun withArgs(vararg args: Any): String {
        val destination = StringBuilder()
        args.forEach { arg ->
            destination.append("/$arg")
        }
        return name + destination
    }
}