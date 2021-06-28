package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import com.agoda.kakao.check.KCheckBox
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.android.architecture.blueprints.todoapp.R

object ToDoDetailsScreen: Screen<ToDoDetailsScreen>() {

    val deleteToDoButton = KButton { withId(R.id.menu_delete) }

    val todoDetailTitle = KTextView { withId(R.id.task_detail_title_text) }

    val todoDetailDescription = KTextView { withId(R.id.task_detail_description_text) }

    val todoDetailCheckBox = KCheckBox { withId(R.id.task_detail_complete_checkbox) }


}
