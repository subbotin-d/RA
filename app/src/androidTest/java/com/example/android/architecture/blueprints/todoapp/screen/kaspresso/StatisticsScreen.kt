package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.text.KTextView
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.screen.BaseScreen

object StatisticsScreen : BaseScreen<StatisticsScreen>() {

    val activeTasksLabel = KTextView { withId(R.id.stats_active_text) }
    val completedTodoLabel = KTextView { withId(R.id.stats_completed_text) }
}