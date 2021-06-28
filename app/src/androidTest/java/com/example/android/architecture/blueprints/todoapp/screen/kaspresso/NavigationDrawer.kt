package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.drawer.KDrawerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.android.architecture.blueprints.todoapp.R

object NavigationDrawer: Screen<NavigationDrawer>() {

    val burgerMenu = KDrawerView { withId(R.id.drawer_layout) }
    val statisticsButton = KButton { withId(R.id.statistics_fragment_dest) }
}