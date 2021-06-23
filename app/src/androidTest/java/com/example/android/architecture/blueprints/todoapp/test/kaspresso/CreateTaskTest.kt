package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.AddTaskScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test

class CreateTaskTest: MyTestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun addNewTask() = before {

    }.after {

    }.run {

        TaskListScreen {
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

        TaskListScreen {
            taskList {
                childAt<TaskListScreen.TaskListItem>(2) {
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