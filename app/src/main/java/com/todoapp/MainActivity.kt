package com.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.todoapp.data.TodoDatabase
import com.todoapp.data.TodoRepository
import com.todoapp.ui.theme.TodoAppTheme
import com.todoapp.view.TodoScreen
import com.todoapp.viewmodel.TodoViewmodel
import com.todoapp.viewmodel.TodoViewmodelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            name = "todo_db"
        ).fallbackToDestructiveMigration(dropAllTables = true).build()
        val repository = TodoRepository(db.todoDao())
        val factory = TodoViewmodelFactory(repository)
        setContent {
            val viewmodel : TodoViewmodel = viewModel(factory = factory)
            TodoAppTheme {
                TodoScreen(viewmodel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppTheme {
        Greeting("Android")
    }
}