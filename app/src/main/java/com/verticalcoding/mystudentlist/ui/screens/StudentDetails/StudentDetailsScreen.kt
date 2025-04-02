package com.verticalcoding.mystudentlist.ui.screens

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.verticalcoding.mystudentlist.data.DogsPhotosRepository
import com.verticalcoding.mystudentlist.model.DogPhoto
import com.verticalcoding.mystudentlist.model.StudentScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.verticalcoding.mystudentlist.StudentsListApplication
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailsScreen(
    route: StudentScreen,
    uiState: StudentDetailsViewModel.UiState,
    retryAction: () -> Unit,
    navController: NavController,
    onDeleteStudent: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Details")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onDeleteStudent(route.name)
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { inner ->
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(inner)){
            Text(
                text = route.name,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            ContentScreen(uiState, retryAction)
        }
    }
}

@Composable
fun ContentScreen(uiState: StudentDetailsViewModel.UiState, retryAction: () -> Unit, modifier: Modifier = Modifier) {
    when(uiState) {
        is StudentDetailsViewModel.UiState.Loading -> LoadingScreen(
            modifier = modifier
        )
        is StudentDetailsViewModel.UiState.Error -> ErrorScreen(
            retryAction = retryAction, modifier = modifier
        )
        is StudentDetailsViewModel.UiState.Success -> {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(uiState.photo.message)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
        }

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Warning, contentDescription = ""
        )
        Text(text = "Failed", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Image(imageVector = Icons.Default.Refresh, contentDescription = null)
        }
    }
}