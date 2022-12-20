package com.andriawan.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andriawan.common.navigation.Routes
import com.andriawan.common_ui.CardColor
import com.andriawan.common_ui.DarkCardColor
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.template.navigation.MainNavigation
import com.andriawan.template.ui.components.BottomNav
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemplateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val navController = rememberNavController()
                    var visible by remember { mutableStateOf(true) }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    visible = when (navBackStackEntry?.destination?.route) {
                        Routes.LIKED_PAGE,
                        Routes.HOME_PAGE -> true
                        else -> false
                    }

                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(visible = visible) {
                                BottomAppBar(
                                    cutoutShape = CircleShape,
                                    backgroundColor = if (isSystemInDarkTheme()) DarkCardColor else CardColor
                                ) {
                                    BottomNav(navHostController = navController)
                                }
                            }
                        }
                    ) {
                        Column(modifier = Modifier.padding(it)) {
                            MainNavigation(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
