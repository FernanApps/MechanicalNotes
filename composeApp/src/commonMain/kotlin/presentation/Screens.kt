package presentation;

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import presentation.screens.details.bundleKeyNoteId

enum class Screens(
    private val args: List<String>? = null,
    val title: StringResource? = null,
    val isTabItem: Boolean = false,
    val unselectedIcon: ImageVector? = null,
    val selectedIcon: ImageVector? = null,
) {
    Splash,
    Login,
    Home,
    Details(args = listOf(bundleKeyNoteId))
    ;

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
            val finalArg = when (arg) {
                is Number -> arg.toString()
                else -> arg
            }
            destination.append("/$finalArg")
        }
        return name + destination
    }

}