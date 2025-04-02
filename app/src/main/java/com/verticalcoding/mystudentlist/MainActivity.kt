package com.verticalcoding.mystudentlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.verticalcoding.mystudentlist.model.StudentList
import com.verticalcoding.mystudentlist.model.StudentScreen
import com.verticalcoding.mystudentlist.ui.screens.StudentDetailsViewModel
import com.verticalcoding.mystudentlist.ui.screens.StudentsList
import com.verticalcoding.mystudentlist.ui.theme.MyStudentListTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.verticalcoding.mystudentlist.ui.screens.StudentDetailsScreen

class MainActivity : ComponentActivity() {

    private var name by mutableStateOf("Łukasz")
//    private var students by mutableStateOf(setOf<String>())
    private var students by mutableStateOf(emptyList<String>())

    @OptIn(ExperimentalMaterial3Api::class)
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
                        val viewModel: StudentDetailsViewModel =
                            viewModel(factory = StudentDetailsViewModel.Factory)
                        StudentDetailsScreen(
                            args,
                            viewModel.uiState,
                            viewModel::getDogImage,
                            navigationController
                        ) {
                            students = students - it
                        }
                    }
                }
            }
        }
    }
}
