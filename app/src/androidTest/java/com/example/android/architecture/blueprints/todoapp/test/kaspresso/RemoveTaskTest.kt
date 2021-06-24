package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToolbarScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test

class RemoveTaskTest: MyTestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun removeTask() = run {

        TaskListScreen {
            taskList {
                firstChild<TaskListScreen.TaskListItem> {
                    checkBox {
                        click()
                    }
                }
            }
        }

        ToolbarScreen {

            pressMenuKey()

            clear {
                click()
            }
        }

        TaskListScreen {
            taskList {
                hasSize(1)
                firstChild<TaskListScreen.TaskListItem> {
                    taskTitle.hasNoText("Build tower in Pisa")
                }
            }
        }
    }
}