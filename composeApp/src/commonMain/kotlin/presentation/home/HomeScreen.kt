package presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import domain.model.Note
import mechanicalnotes.composeapp.generated.resources.Res
import mechanicalnotes.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.NoteViewModel
import presentation.components.HighlightedText

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: NoteViewModel = koinViewModel()
) {
    val notes by viewModel.notes.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    var showSearchBar by remember {
        mutableStateOf(false)
    }

    val primaryColor = MaterialTheme.colorScheme.primary


    /*
    if(notes.isEmpty()){
         Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
             CircularProgressIndicator()
         }
    } else {
        */
    LazyColumn(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        item() {
            Text(
                stringResource(Res.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    viewModel.onSearchTextChanged(it)
                },
                modifier = Modifier
                    //.weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onSearchTextChanged("")
                    }) {
                        Icon(Icons.Filled.Close, "Delete Text Search", tint = primaryColor)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    //focusedIndicatorColor = Color.Transparent,
                    //unfocusedIndicatorColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.size(20.dp))

        }

        /*
        items(notes){ note ->
            NoteItem(modifier = Modifier.padding(5.dp), note = note){

            }
        }
        */
        items(notes) { note ->
            NoteItem(modifier = Modifier.padding(5.dp), note = note, searchText = searchText) { }
        }
    }

    //}
}


/*
@Composable
fun NoteItem(modifier: Modifier = Modifier, note: Note, onClick:(Note) -> Unit) {
    Card(onClick = {
        onClick(note)
    }, modifier = modifier) {
        Column (modifier = Modifier.padding(7.5.dp)){
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(note.vehicle, modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold))
                Text(note.date.date.toString())
            }
            note.anomalies.forEach {
                Text(it.description, modifier = Modifier.fillMaxWidth())
            }

        }
    }
}
*/

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    searchText: String,
    onClick: (Note) -> Unit
) {

    val colorPrimary = MaterialTheme.colorScheme.primary
    Card(onClick = { onClick(note) }, modifier = modifier) {
        Column(modifier = Modifier.padding(7.5.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                HighlightedText(
                    text = note.vehicle,
                    textHighlighted = searchText,
                    textHighlightedColor = colorPrimary,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(note.date.toString())
            }
            note.anomalies.forEach {
                HighlightedText(it.description, searchText, colorPrimary, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}



