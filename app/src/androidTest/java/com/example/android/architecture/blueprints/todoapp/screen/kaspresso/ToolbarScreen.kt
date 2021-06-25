package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.toolbar.KToolbar
import com.example.android.architecture.blueprints.todoapp.R

object ToolbarScreen: Screen<ToolbarScreen>() {

    val clear = KButton { withText("Clear completed") }

    val completed = KButton { withText("Completed") }

    val filter = KButton { withId(R.id.menu_filter) }
}