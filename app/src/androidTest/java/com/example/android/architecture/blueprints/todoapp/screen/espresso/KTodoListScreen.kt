package com.example.android.architecture.blueprints.todoapp.screen.espresso

import com.example.android.architecture.blueprints.todoapp.R
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

object KTodoListScreen : Screen<KTodoListScreen>() {

	val addTaskFab = KButton { withId(R.id.add_task_fab) }
}