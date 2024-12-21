package com.verticalcoding.mystudentlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import com.verticalcoding.mystudentlist.ui.theme.MyStudentListTheme

class MainActivity : ComponentActivity() {

    private var name by mutableStateOf("Łukasz")
    private var students by mutableStateOf(setOf<String>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyStudentListTheme {
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
                                onValueChange = { name = it }
                            )

                            OutlinedButton(onClick = {
                                students += name
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null
                                )
                            }
                        }

                        LazyRow(
                            modifier = Modifier
                                .padding(top=16.dp)
                        ) {
                            items(students.toList()) { studentName ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp, horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = studentName,
                                        fontWeight = FontWeight.SemiBold
                                    )

                                    IconButton(onClick = {
                                        students = students.minus(studentName)
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
        }
    }
}

@Composable
fun GreetingColumn(name: String, onNameChange: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top=100.dp)
            .height(100.dp)
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text(
            text = "Hello $name!"
        )
        Button(
            enabled = validateName(name),
            onClick = {
            val name = getRandomName()
            print(name)
            onNameChange(name)
        }) {
            Text(text = "Random Name")
        }
    }
}

@Composable
fun GreetingRow(name: String, onNameChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(top=100.dp)
            .height(100.dp)
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text(
            text = "Hello $name!"
        )
        FilledTonalButton (
            enabled = validateName(name),
            onClick = {
            val name = getRandomName()
            print(name)
            onNameChange(name)
        }) {
            Text(text = "Random Name")
        }
    }
}

fun validateName(name: String): Boolean {
    return name.isNotEmpty() && name.length > 3
}

fun getRandomName(): String {
    val names = listOf(
        "Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "Elijah", "Charlotte",
        "William", "Sophia", "James", "Amelia", "Benjamin", "Isabella", "Lucas", "Mia",
        "Henry", "Evelyn", "Alexander", "Harper", "Michael", "Luna", "Daniel", "Ella",
        "Matthew", "Elizabeth", "Samuel", "Sofia", "Jackson", "Emily", "Joseph", "Avery",
        "Sebastian", "Scarlett", "David", "Grace", "Carter", "Chloe", "Owen", "Victoria",
        "Wyatt", "Riley", "John", "Aria", "Jack", "Lily", "Luke", "Aubrey", "Jayden", "Zoey",
        "Dylan", "Addison", "Grayson", "Lillian", "Levi", "Natalie", "Isaac", "Hannah",
        "Gabriel", "Brooklyn", "Julian", "Samantha", "Mateo", "Zoe", "Anthony", "Eleanor",
        "Jaxon", "Leah", "Lincoln", "Audrey", "Joshua", "Skylar", "Christopher", "Ellie",
        "Andrew", "Paisley", "Theodore", "Violet", "Caleb", "Stella", "Ryan", "Aurora",
        "Asher", "Hazel", "Nathan", "Aaliyah", "Thomas", "Madelyn", "Leo", "Elena",
        "Isaiah", "Sarah", "Charles", "Ariana", "Josiah", "Penelope", "Hudson", "Lila",
        "Christian", "Layla", "Hunter", "Nora", "Connor", "Reese", "Eli", "Mackenzie",
        "Ezra", "Madeline", "Aaron", "Abigail", "Nicholas", "Willow", "Cameron", "Sadie",
        "Adrian", "Quinn", "Jonathan", "Caroline", "Nolan", "Allison", "Jeremiah", "Genesis",
        "Easton", "Eva", "Elias", "Piper", "Colton", "Gianna", "Carson", "Serenity",
        "Robert", "Autumn", "Angel", "Nevaeh", "Brayden", "Jocelyn", "Jordan", "Faith",
        "Nicholas", "Bella", "Dominic", "Katherine", "Austin", "Alexandra", "Ian", "Kylie",
        "Adam", "Brianna", "Elias", "Anna", "Jaxson", "Mary", "Greyson", "Ashley",
        "Jose", "Isabelle"
    )

    return names.random()
}


@Preview(showBackground = true)
@Composable
fun GreetingColumnPreview() {
    MyStudentListTheme {
        GreetingColumn("Android", onNameChange = { name ->
            print(name)
        })
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingRowPreview() {
    MyStudentListTheme {
        GreetingRow("Android", onNameChange = { name ->
            print(name)
        })
    }
}