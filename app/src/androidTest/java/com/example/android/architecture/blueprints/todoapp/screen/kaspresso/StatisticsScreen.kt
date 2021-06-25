package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.android.architecture.blueprints.todoapp.R

object StatisticsScreen: Screen<StatisticsScreen>() {

    val active = KTextView { withId(R.id.stats_active_text) }

    val completed = KTextView { withId(R.id.stats_completed_text) }
}