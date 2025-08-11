package com.verticalcoding.dogslist.ui.screens.DogsList

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.verticalcoding.dogslist.model.Dog
import com.verticalcoding.dogslist.model.StudentScreen

@Composable
fun DogsScreen(
    viewModel: DogsListViewModel,
    navigationController: NavController
) {
    val items by viewModel.uiState.collectAsStateWithLifecycle()
    if (items is DogsListViewModel.UiState.Success) {
        DogsList(
            name = viewModel.name.value,
            dogs = (items as DogsListViewModel.UiState.Success).data,
            navController = navigationController,
            onNameChange = { viewModel.name.value = it},
            onSave = viewModel::addDog,
            onFav = viewModel::triggerFav,
            onTrash = viewModel::removeDog
        )
    }
}

@Composable
fun DogsList(
    name: String,
    dogs: List<Dog>,
    navController: NavController,
    onNameChange: (name: String) -> Unit,
    onSave: (name: String) -> Unit,
    onFav: (id:Int) -> Unit,
    onTrash: (id:Int) -> Unit,
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
                        onSave(name)
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
                items(dogs) { dog ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .clickable {
                                navController.navigate(StudentScreen(dog.name))
                            }
                    ) {
                        Text(
                            text = dog.name,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(Modifier.weight(1f))

                        val brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF6A5ACD), // Purple
                                Color(0xFFFFC0CB)  // Pink
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(50f, 100f)
                        )

                        IconButton(
                            onClick = {
                                onFav(dog.id)
                            }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .graphicsLayer(alpha = 0.99f)
                                    .drawWithCache {
                                        onDrawWithContent {
                                            drawContent()
                                            drawRect(brush, blendMode = BlendMode.SrcAtop)
                                        }
                                    },
                                imageVector = if (dog.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                            )
                        }

                        IconButton(onClick = {
                            onTrash(dog.id)
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