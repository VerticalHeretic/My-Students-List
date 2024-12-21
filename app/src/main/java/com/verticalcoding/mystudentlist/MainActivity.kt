package com.verticalcoding.mystudentlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.verticalcoding.mystudentlist.data.models.StudentList
import com.verticalcoding.mystudentlist.data.models.StudentScreen
import com.verticalcoding.mystudentlist.presentation.components.GreetingBox
import com.verticalcoding.mystudentlist.presentation.screens.StudentsList
import com.verticalcoding.mystudentlist.ui.theme.MyStudentListTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private var name by mutableStateOf("Łukasz")
    private var students by mutableStateOf(setOf<String>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyStudentListTheme {
                val navigationController = rememberNavController()
                NavHost(navController = navigationController, startDestination = StudentList) {
                    composable<StudentList> {
                        StudentsList(
                            name = name,
                            students = students,
                            navController = navigationController,
                            onNameChange = { name = it },
                            onAddStudent = { studentName ->
                                students = students + studentName
                            },
                            onDeleteStudent = { studentName ->
                                students = students - studentName
                            }
                        )
                    }
                    composable<StudentScreen> {
                        val args = it.toRoute<StudentScreen>()

                        Text(text=args.name, modifier = Modifier.fillMaxSize(), fontSize = 72.sp)
                    }
                }
            }
        }
    }
}
