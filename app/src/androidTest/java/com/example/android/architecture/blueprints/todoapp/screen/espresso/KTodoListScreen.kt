package com.example.android.architecture.blueprints.todoapp.screen.espresso

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.android.architecture.blueprints.todoapp.R

object KTodoListScreen : Screen<KTodoListScreen>() {

	val addTaskFab = KButton { withId(R.id.add_task_fab) }
}