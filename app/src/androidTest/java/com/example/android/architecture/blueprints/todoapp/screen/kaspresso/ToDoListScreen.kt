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

object ToDoListScreen: Screen<ToDoListScreen>() {

    val addToDoButton = KButton { withId(R.id.add_task_fab) }

    val todos = KRecyclerView(
        builder = {
            withId(R.id.tasks_list)
        },
        itemTypeBuilder = {
            itemType(::TodoItem)
        }
    )

    class TodoItem(parent: Matcher<View>) : KRecyclerItem<TodoItem>(parent) {

        val todoCheckBox = KCheckBox(parent) { withId(R.id.complete_checkbox) }
        val todoTitle = KTextView(parent) { withId(R.id.title_text) }
    }

    val menuFilterToDoButton = KButton { withId(R.id.menu_filter) }
    val menuFilterItemCompleted = KButton { withText("Completed") }
    val filteringTitle = KTextView{ withId(R.id.filtering_text) }

}