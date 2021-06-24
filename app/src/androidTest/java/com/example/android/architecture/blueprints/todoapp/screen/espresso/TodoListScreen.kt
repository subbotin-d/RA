package com.example.android.architecture.blueprints.todoapp.screen.espresso

import android.view.View
import com.example.android.architecture.blueprints.todoapp.R
import io.github.kakaocup.kakao.check.KCheckBox
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object TodoListScreen : Screen<TodoListScreen>() {

	val addButton = KButton { withId(R.id.add_task_fab) }

	val taskList = KRecyclerView(
		builder = {
			withId(R.id.tasks_list)
		},
		itemTypeBuilder = {
			itemType(::TaskListItem)
		}
	)

	class TaskListItem(parent: Matcher<View>) : KRecyclerItem<TaskListItem>(parent) {

		val checkBox = KCheckBox(parent) { withId(R.id.complete_checkbox) }

		val taskTitle = KTextView(parent) { withId(R.id.title_text) }
	}
}