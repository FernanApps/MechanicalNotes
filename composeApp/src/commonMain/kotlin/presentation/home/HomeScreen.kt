package presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.NoteViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    viewModel: NoteViewModel = koinViewModel()
) {
    val notes by viewModel.notes.collectAsState()

    Text("HOla " + notes)
}