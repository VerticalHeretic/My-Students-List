package com.verticalcoding.mystudentlist.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.verticalcoding.mystudentlist.model.StudentScreen

@Composable
fun StudentsList(
    name: String,
    students: List<String>,
    navController: NavController,
    onNameChange: (String) -> Unit,
    onAddStudent: (String) -> Unit,
    onDeleteStudent: (String) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                OutlinedTextField(
                    value = name,
                    onValueChange = { onNameChange(it) }
                )

                OutlinedButton(
                    enabled = name.isNotEmpty() && name.length > 3,
                    onClick = {
                    onAddStudent(name)
                    onNameChange("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(top=16.dp)
            ) {
                items(students.toList()) { studentName ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .clickable {
                                navController.navigate(StudentScreen(studentName))
                            }
                    ) {
                        Text(
                            text = studentName,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(Modifier.weight(1f))

                        IconButton(onClick = {
                            onDeleteStudent(studentName)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}