package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.navigation.KNavigationView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.Matchers

object SideDrawerScreen : Screen<SideDrawerScreen>() {

    val hamburgerMenu = KButton {
        withParent { withId(R.id.toolbar) }
        withClassName(Matchers.endsWith("ImageButton"))
    }
    val sideDrawer = KNavigationView { withId(R.id.nav_view) }

    fun openTaskListScreen() {
        openScreen(R.id.tasks_list)
    }

    fun openStatisticsScreen() {
        openScreen(R.id.statistics_fragment_dest)
    }

    private fun openScreen(screenId : Int) {
        hamburgerMenu.click()
        sideDrawer.isDisplayed()
        sideDrawer.navigateTo(screenId)
    }
}