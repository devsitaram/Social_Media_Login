package com.edu.socialmediallogin.navigate

const val NAME_KEY = "name_key"
const val AGE_KEY = "age_key"

sealed class Screen(val route: String) {
    object Welcome: Screen(route = "welcome_screen")
    object Details: Screen(route = "details_screen/{$NAME_KEY}/{$AGE_KEY}")
}