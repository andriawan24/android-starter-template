package com.andriawan.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andriawan.common.BottomNavDestination
import com.andriawan.common.Routes
import com.andriawan.common_ui.CardColor
import com.andriawan.common_ui.FloatingButtonColor
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.common_ui.UnselectedBottomNavColor
import com.andriawan.template.ui.components.MainNavigation
import com.andriawan.template.ui.pages.home.HomeScreen
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
                    color = MaterialTheme.colors.background
                ) {
                    MyApp {
                        val navController = rememberNavController()
                        var visible by remember { mutableStateOf(true) }
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        visible = when (navBackStackEntry?.destination?.route) {
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
                                        backgroundColor = CardColor
                                    ) {
                                        BottomNav(navHostController = navController)
                                    }
                                }
                            },
                            floatingActionButton = {
                                AnimatedVisibility(visible = visible) {
                                    FloatingActionButton(
                                        onClick = {
                                            Timber.d("")
                                        },
                                        backgroundColor = FloatingButtonColor
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            floatingActionButtonPosition = FabPosition.Center,
                            isFloatingActionButtonDocked = true,
                        ) {
                            MainNavigation(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNav(navHostController: NavHostController) {
    val screens = remember { BottomNavDestination.values().toList() }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = CardColor
    ) {
        screens.forEach { screen ->
            if (screen == BottomNavDestination.BLANK_PAGE) {
                BottomNavigationItem(
                    icon = { },
                    label = { },
                    selected = false,
                    onClick = { },
                    enabled = false
                )
            } else {
                AddBottomNav(
                    screen = screen,
                    navController = navHostController,
                    currentDestination = currentDestination
                )
            }
        }
    }
}

@Composable
fun RowScope.AddBottomNav(
    screen: BottomNavDestination,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            screen.routeName
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.routeName
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.routeName
        } == true,
        selectedContentColor = Color.White,
        unselectedContentColor = UnselectedBottomNavColor,
        onClick = {
            navController.navigate(screen.routeName) {
                launchSingleTop = true
                popUpTo(
                    navController.graph.findStartDestination().id
                )
            }
        }
    )
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}
