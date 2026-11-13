package com.todoapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.todoapp.viewmodel.TodoViewmodel

@Composable
fun TodoScreen(viewmodel : TodoViewmodel) {
    val todo by viewmodel.todos.collectAsState()
    var text by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(Modifier.padding(padding).padding(vertical = 16.dp, horizontal = 5.dp)) {
            Row {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Enter a task") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(10.dp))
                Button(shape = RoundedCornerShape(3.dp), onClick = {
                    if (text.isNotBlank()) {
                        viewmodel.addTodo(text)
                        text = ""
                    }
                }, modifier = Modifier.height(50.dp)) {
                    Text("add something")


                }
            }
            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(todo) { todos ->
                    TodoItem(
                        todo = todos,
                        onDelete = { viewmodel.deleteTodo(it) },
                        onToggle = { viewmodel.toggleDone(it) })
                }
            }
        }
    }
}