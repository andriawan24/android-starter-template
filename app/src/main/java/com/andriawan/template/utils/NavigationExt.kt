package com.andriawan.template.utils

import androidx.navigation.NavHostController

fun NavHostController.navigateWithParam(route: String, vararg params: String) {
    this.navigate("$route/${params.joinToString(separator = "/")}")
}