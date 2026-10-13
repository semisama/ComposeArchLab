package uz.ttpu.composearchlab.ui.signin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInRoute(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SignInScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChanged,
        onSignInClick = { /* Sign in action */ },
        modifier = modifier
    )
}

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    onEmailChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSignInClick,
            enabled = uiState.isSignInEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign In")
        }
    }
}