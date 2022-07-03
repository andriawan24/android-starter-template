package com.andriawan.common.navigation

import com.andriawan.common.R

enum class BottomNavDestination(
    val routeName: String = "",
    val icon: Int = 0
) {
    HOME_PAGE(
        routeName = Routes.HOME_PAGE,
        icon = R.drawable.ic_home
    ),
    LIKED_PAGE(
        routeName = Routes.LIKED_PAGE,
        icon = R.drawable.ic_love
    )
//    BLANK_PAGE,
//    SAVED_PAGE(
//        routeName = Routes.SAVED_PAGE,
//        icon = R.drawable.ic_play
//    ),
//    PROFILE_PAGE(
//        routeName = Routes.PROFILE_PAGE,
//        icon = R.drawable.ic_user
//    )
}