package com.verticalcoding.mystudentlist.model

import kotlinx.serialization.Serializable

@Serializable
object StudentList

@Serializable
data class StudentScreen(val name: String)
