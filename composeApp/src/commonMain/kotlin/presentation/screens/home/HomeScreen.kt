package presentation.screens.home

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
import domain.model.Note
import mechanicalnotes.composeapp.generated.resources.Res
import mechanicalnotes.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import presentation.NoteViewModel
import presentation.components.HighlightedText
import presentation.theme.Dimens
import utils.getDateFormatSpanish

@Composable
fun HomeScreen(
    onNavigateDetails: (id: Long) -> Unit,
    viewModel: NoteViewModel
) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()

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
    Box {

        LazyColumn(modifier = Modifier.fillMaxSize().padding(Dimens.defaultPadding)) {
            item() {
                Text(
                    stringResource(Res.string.app_name),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(Dimens.defaultPadding))

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
                Spacer(modifier = Modifier.size(Dimens.defaultPadding))

            }

            items(notes) { note ->
                NoteItem(
                    modifier = Modifier.padding(5.dp),
                    note = note,
                    searchText = searchText
                ) {
                    onNavigateDetails(it.id)
                }
            }
        }


        FloatingActionButton(
            onClick = {
                onNavigateDetails(0)
            },
            modifier = Modifier.padding(Dimens.defaultPadding).align(Alignment.BottomEnd),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Rounded.Add, "Add")
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

    val textHighlightedColor = MaterialTheme.colorScheme.onPrimaryContainer
    Card(onClick = { onClick(note) }, modifier = modifier) {
        Column(modifier = Modifier.padding(7.5.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                HighlightedText(
                    text = note.vehicle,
                    textHighlighted = searchText,
                    textHighlightedColor = textHighlightedColor,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(note.date.toString().let(::getDateFormatSpanish))
            }
            note.anomalies.forEach {
                HighlightedText(
                    it.description,
                    searchText,
                    textHighlightedColor,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}



