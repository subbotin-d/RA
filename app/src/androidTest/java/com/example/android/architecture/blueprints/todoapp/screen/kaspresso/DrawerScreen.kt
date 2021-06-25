package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.drawer.KDrawerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.android.architecture.blueprints.todoapp.R

object DrawerScreen: Screen<DrawerScreen>() {

    val drawer = KDrawerView { withId(R.id.drawer_layout) }

    val statistics = KButton { withId(R.id.statistics_fragment_dest) }
}