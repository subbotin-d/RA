package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.android.architecture.blueprints.todoapp.R

object AddTaskScreen: Screen<AddTaskScreen>() {

    val title = KEditText { withId(R.id.add_task_title_edit_text) }

    val description = KEditText { withId(R.id.add_task_description_edit_text) }

    val saveButton = KButton { withId(R.id.save_task_fab) }
}