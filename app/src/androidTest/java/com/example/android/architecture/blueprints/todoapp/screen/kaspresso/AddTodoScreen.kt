package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.text.KButton
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.screen.BaseScreen

object AddTodoScreen : BaseScreen<AddTodoScreen>() {

    val titleInput = KEditText { withId(R.id.add_task_title_edit_text) }
    val descriptionInput = KEditText { withId(R.id.add_task_description_edit_text) }
    val saveFab = KButton { withId(R.id.save_task_fab)}
}