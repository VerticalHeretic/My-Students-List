package com.verticalcoding.mystudentlist.data.models

import kotlinx.serialization.Serializable

@Serializable
object StudentList

@Serializable
data class StudentScreen(val name: String)
