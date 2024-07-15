package presentation.screens.login

import presentation.LoginViewModel

/*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import mechanicalnotes.composeapp.generated.resources.Res
import mechanicalnotes.composeapp.generated.resources.foreground
import mechanicalnotes.composeapp.generated.resources.password
import mechanicalnotes.composeapp.generated.resources.user
import mechanicalnotes.composeapp.generated.resources.login
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(onNavigate: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(Res.drawable.foreground),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Username TextField
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(Res.string.user)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(Res.string.password)) },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = { /* Handle login */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(Res.string.login))
        }
    }
}
*/


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import domain.model.User
import mechanicalnotes.composeapp.generated.resources.Res
import mechanicalnotes.composeapp.generated.resources.foreground
import mechanicalnotes.composeapp.generated.resources.password
import mechanicalnotes.composeapp.generated.resources.email
import mechanicalnotes.composeapp.generated.resources.login
import mechanicalnotes.composeapp.generated.resources.logout
import mechanicalnotes.composeapp.generated.resources.email_or_password
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.LoginUiState

@Composable
fun LoginScreen(
    onSuccess: () -> Unit,
    viewModel: LoginViewModel,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val emailError by viewModel.emailError.collectAsStateWithLifecycle()
    val passwordError by viewModel.passwordError.collectAsStateWithLifecycle()
    val isProcessing by viewModel.isProcessing.collectAsStateWithLifecycle()
    val isButtonEnabled by viewModel.isButtonEnabled.collectAsStateWithLifecycle()
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()

    LaunchedEffect(true){
        viewModel.checkCurrentUser()
    }


    LoginScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSignInClick() },
        isProcessing = isProcessing,
        isButtonEnabled = isButtonEnabled,
        currentUser = currentUser,
        isError = emailError || passwordError,
        onSignOut = viewModel::onSignOut,
        onSignIn = onSuccess
    )

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    isProcessing: Boolean,
    isButtonEnabled: Boolean,
    currentUser: User?,
    isError: Boolean,
    onSignOut: () -> Unit,
    onSignIn: () -> Unit,

    ) {


    if(currentUser != null && !currentUser.isAnonymous){
        //onSignIn()
    }


    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        val width = this.maxWidth
        val finalModifier = if (width >= 780.dp) modifier.width(400.dp) else modifier.fillMaxWidth()
        Column(
            modifier = finalModifier.padding(16.dp).fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(Res.drawable.foreground),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(150.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(), value = uiState.email, label = {
                    Text(stringResource(Res.string.email))
                }, onValueChange = onEmailChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password,
                visualTransformation = PasswordVisualTransformation(),
                label = {
                    Text(stringResource(Res.string.password))
                },
                onValueChange = onPasswordChange
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isProcessing) {
                CircularProgressIndicator()
            } else {
                Button(
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    onClick = onSignInClick,
                    enabled = isButtonEnabled
                ) {
                    Text(stringResource(Res.string.login))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //This is just for example, Ideally user will go to some other screen after login
            /*
            AnimatedVisibility(currentUser != null && !currentUser.isAnonymous) {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text("Login Successful", color = Color.Green.copy(alpha = 0.5f))
                    Text("Logged In auth.User ID:")
                    Text("${currentUser?.id}")
                    TextButton(
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            onSignOut()
                        }) {
                        Text(stringResource(Res.string.logout))
                    }
                }
            }
            */

            AnimatedVisibility(isError && !isProcessing) {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(stringResource(Res.string.email_or_password), color = MaterialTheme.colorScheme.error)
                }
            }

        }
    }

}