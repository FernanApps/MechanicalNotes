package presentation.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.LocalShipping
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mechanicalnotes.composeapp.generated.resources.Res
import mechanicalnotes.composeapp.generated.resources.cancel
import mechanicalnotes.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.stringResource
import presentation.NoteViewModel
import presentation.components.MyDatePickerDialog
import presentation.theme.Dimens
import utils.TimeExt.convertMillisToDate
import utils.TimeExt.toTimeToMillis
import utils.getDateFormatSpanish


internal const val bundleKeyNoteId = "bundleKeyNoteId"

@Composable
fun DetailScreen(
    id: Long,
    onBack: () -> Unit,
    viewModel: NoteViewModel
) {

    val note by viewModel.currentNote.collectAsState()

    var date by remember {
        mutableStateOf("Open date picker dialog")
    }


    var showDatePicker by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(true) {
        viewModel.setCurrentNote(id)
    }

    if (showDatePicker) {

        MyDatePickerDialog(
            initialSelectedDateMillis = note.date.toTimeToMillis(),
            onDateSelected = { dateMillis: Long? ->
                dateMillis?.let {
                    println("select MyDatePickerDialog $dateMillis")
                    viewModel.setDate(dateMillis)
                    showDatePicker = false
                }
            },
            confirmButtonText = stringResource(Res.string.ok),
            cancelButtonText = stringResource(Res.string.cancel),
            onDismiss = {
                showDatePicker = false
            }
        )
    }

    Column(modifier = Modifier.padding(Dimens.defaultPadding)) {
        Text(note.toString())

        IconButton(onClick = {
            onBack()
        }) {
            Icon(Icons.Rounded.ArrowBackIosNew, "back")
        }

        Row {

            OutlinedTextField(
                value = note.vehicle,
                onValueChange = {
                    viewModel.setVehicle(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    Icon(Icons.Rounded.LocalShipping, "")
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    //focusedIndicatorColor = Color.Transparent,
                    //unfocusedIndicatorColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.size(Dimens.defaultPadding))

            OutlinedTextField(
                value = note.date.toString().let(::getDateFormatSpanish),
                onValueChange = {
                    viewModel.setVehicle(it)
                },
                readOnly = true,
                modifier = Modifier.padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        showDatePicker = true
                    }) {
                        Icon(Icons.Rounded.CalendarMonth, "")
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    //focusedIndicatorColor = Color.Transparent,
                    //unfocusedIndicatorColor = Color.Gray
                )
            )

        }

    }

}





