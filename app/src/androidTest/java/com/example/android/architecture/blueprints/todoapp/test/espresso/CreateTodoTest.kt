package com.example.android.architecture.blueprints.todoapp.test.espresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.espresso.KTodoListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class CreateTodoTest : TestCase() {

	@get:Rule
	val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

	@Test
	fun checkAddNewTodo() {
		run {
			KTodoListScreen {
				addTaskFab {
					isDisplayed()
				}
			}
		}
	}
}