package com.example.android.architecture.blueprints.todoapp.screen.espresso

import com.example.android.architecture.blueprints.todoapp.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

object AddTaskScreen : Screen<AddTaskScreen>() {

	val title = KEditText { withId(R.id.add_task_title_edit_text) }

	val description = KEditText { withId(R.id.add_task_description_edit_text) }

	val saveButton = KButton { withId(R.id.save_task_fab) }
}