package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.toolbar.KToolbar
import com.example.android.architecture.blueprints.todoapp.R

object ToolbarScreen: Screen<ToolbarScreen>() {


    val toolbar = KToolbar { withId(R.id.toolbar) }

    val clear = KButton { withText("Clear completed") }

    val refresh = KButton { withId(R.id.menu_refresh) }

    val filter = KButton { withId(R.id.menu_filter) }
}