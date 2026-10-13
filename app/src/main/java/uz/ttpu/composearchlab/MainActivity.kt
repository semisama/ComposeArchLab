package uz.ttpu.composearchlab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.ttpu.composearchlab.ui.signin.SignInRoute
import uz.ttpu.composearchlab.ui.theme.ComposeArchLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArchLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AllTasksScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// all tasks in one screen
@Composable
fun AllTasksScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // --- State Hoisting ---
        Text("---  State Hoisting ---", style = MaterialTheme.typography.titleMedium, color = Color.Gray)
        NameScreen()

        HorizontalDivider()

        // --- TASK 4: Recomposition Optimization ---
        Text("---  Recomposition Optimization ---", style = MaterialTheme.typography.titleMedium, color = Color.Gray)
        NewsScreen()

        HorizontalDivider()

        // --- TASK 5-9: Sign In ViewModel & State ---
        Text("---  Sign In Screen ---", style = MaterialTheme.typography.titleMedium, color = Color.Gray)
        Box(modifier = Modifier.height(300.dp)) {
            SignInRoute()
        }
    }
}

// ==================== TASK 3 ====================
@Composable
fun NameField(name: String, onNameChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = modifier
    )
}

@Composable
fun NameScreen(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        NameField(name = name, onNameChange = { name = it })
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hello, $name!")
    }
}

// ==================== TASK 4 ====================
data class News(val title: String, val subtitle: String, val views: Int)

@Composable
fun Header(news: News) {
    SideEffect { Log.d("Recompose", "Header(news)") }
    Column {
        Text(news.title)
        Text(news.subtitle)
    }
}

@Composable
fun Header(title: String, subtitle: String) {
    SideEffect { Log.d("Recompose", "Header(title, subtitle)") }
    Column {
        Text(title)
        Text(subtitle)
    }
}

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    var news by remember { mutableStateOf(News("Compose is declarative", "State in, UI out", views = 0)) }
    Column(modifier = modifier) {
        Header(news)
        Header(news.title, news.subtitle)
        Text("Views: ${news.views}")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { news = news.copy(views = news.views + 1) }) {
            Text("Add view")
        }
    }
}