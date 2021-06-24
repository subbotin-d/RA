package com.example.android.architecture.blueprints.todoapp.test.espresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.espresso.AddTaskScreen
import com.example.android.architecture.blueprints.todoapp.screen.espresso.TodoListScreen
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
			TodoListScreen {
				addButton {
					click()
				}
			}

			AddTaskScreen {
				title {
					replaceText("Test task title")
				}
				description {
					replaceText("Test task description")
				}
				saveButton {
					click()
				}
			}

			TodoListScreen {
				taskList {
					childAt<TodoListScreen.TaskListItem>(2) {
						checkBox {
							isNotChecked()
						}
						taskTitle {
							isDisplayed()
							hasText("Test task title")
						}
					}
				}
				taskList {
					hasSize(3)
				}
			}
		}
	}
}