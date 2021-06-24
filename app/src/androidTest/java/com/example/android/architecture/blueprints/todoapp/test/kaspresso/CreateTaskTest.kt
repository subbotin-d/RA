package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.AddTaskScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test

// TODO Добавить TODO и проверить что она добавилась
class CreateTaskTest: MyTestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun addNewTask() = run {

        TaskListScreen {
            addButton {
                click()
            }
        }

        AddTaskScreen {
            title {
                replaceText(TEST_TASK_TITLE)
            }
            description {
                replaceText(TEST_TASK_DESCRIPTION)
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
                        hasText(TEST_TASK_TITLE)
                    }
                }
            }
            taskList {
                hasSize(3)
            }
        }
    }
}