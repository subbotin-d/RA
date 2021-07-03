package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.android.architecture.blueprints.todoapp.R

object TodoDetailsScreen : Screen<TodoDetailsScreen>() {

    val titleLabel = KTextView { withId(R.id.task_detail_title_text) }
    val deleteButton = KTextView { withId(R.id.menu_delete) }
}