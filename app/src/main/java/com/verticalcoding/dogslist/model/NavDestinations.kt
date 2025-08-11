package com.verticalcoding.dogslist.model

import kotlinx.serialization.Serializable

@Serializable
object StudentList

@Serializable
data class StudentScreen(val name: String)
