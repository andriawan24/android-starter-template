package com.andriawan.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andriawan.common.navigation.BottomNavDestination
import com.andriawan.common.navigation.Routes
import com.andriawan.common_ui.*
import com.andriawan.template.nav_graph.MainNavigation
import com.andriawan.template.ui.components.BottomNav
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
                    MyApp {
                        val navController = rememberNavController()
                        var visible by remember { mutableStateOf(true) }
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        visible = when (navBackStackEntry?.destination?.route) {
                            Routes.LIKED_PAGE,
                            Routes.HOME_PAGE -> {
                                true
                            }

                            else -> {
                                false
                            }
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
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}
