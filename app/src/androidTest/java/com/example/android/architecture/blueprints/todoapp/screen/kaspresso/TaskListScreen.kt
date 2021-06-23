package com.example.android.architecture.blueprints.todoapp.screen.kaspresso

import android.view.View
import com.agoda.kakao.check.KCheckBox
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.Matcher

object TaskListScreen: Screen<TaskListScreen>() {

    val addButton = KButton { withId(R.id.add_task_fab) }

    val taskList = KRecyclerView(
        builder = {
            withId(R.id.tasks_list)
        },
        itemTypeBuilder = {
            itemType(::TaskListItem)
        }
    )

    class TaskListItem(parent: Matcher<View>): KRecyclerItem<TaskListItem>(parent) {

        val checkBox  = KCheckBox(parent) { withId(R.id.complete_checkbox) }

        val taskTitle = KTextView(parent) { withId(R.id.title_text) }
    }
}