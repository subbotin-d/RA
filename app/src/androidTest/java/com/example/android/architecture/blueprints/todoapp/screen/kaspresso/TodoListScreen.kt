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

object TodoListScreen : Screen<TodoListScreen>() {

    val addTodoFab = KButton { withId(R.id.add_task_fab) }

    val todoList = KRecyclerView (builder = {
        withId(R.id.tasks_list)
    }, itemTypeBuilder = {
        itemType(::TodoListItem)
    })

    val filterMenuButton = KTextView { withId(R.id.menu_filter) }

    val filterMenuCompletedButton = KTextView {
        withId(R.id.title);
        withText("Completed")
    }

    class TodoListItem(parent: Matcher<View>) : KRecyclerItem<TodoListItem>(parent) {
        val titleLabel = KTextView(parent) { withId(R.id.title_text)}
        val doneCheckbox = KCheckBox(parent) { withId(R.id.complete_checkbox) }
    }
}