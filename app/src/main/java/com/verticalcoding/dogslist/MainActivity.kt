package com.verticalcoding.dogslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.verticalcoding.dogslist.model.StudentList
import com.verticalcoding.dogslist.model.StudentScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.verticalcoding.dogslist.ui.screens.DogDetails.StudentDetailsScreen
import com.verticalcoding.dogslist.ui.screens.DogDetails.DogDetailsViewModel
import com.verticalcoding.dogslist.ui.screens.DogsList.DogsScreen
import com.verticalcoding.dogslist.ui.screens.DogsList.DogsListViewModel
import com.verticalcoding.dogslist.ui.theme.DogsListTheme

class MainActivity : ComponentActivity() {

    private var students by mutableStateOf(emptyList<String>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsListTheme {
                val navigationController = rememberNavController()
                NavHost(navController = navigationController, startDestination = StudentList) {
                    composable<StudentList> {
                        val viewModel: DogsListViewModel =
                            viewModel(factory = DogsListViewModel.Factory)
                        DogsScreen(
                            viewModel = viewModel,
                            navigationController = navigationController
                        )
                    }
                    composable<StudentScreen> {
                        val args = it.toRoute<StudentScreen>()
                        val viewModel: DogDetailsViewModel =
                            viewModel(factory = DogDetailsViewModel.Factory)
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
