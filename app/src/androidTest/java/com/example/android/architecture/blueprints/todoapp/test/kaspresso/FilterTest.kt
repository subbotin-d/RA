package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.AddTaskScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.TaskListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToolbarScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.Test


class FilterTest: MyTestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    @Test
    fun filterTest() = run {

        addTasks(3)

        setCompleted()

        ToolbarScreen {
            filter {
                click()
            }
            completed {
                click()
            }
        }

        TaskListScreen {
            checkCompletedDisplayed()
        }
    }

    private fun addTasks(quantity: Int) {

        for (i in 1..quantity) {

            TaskListScreen {
                addButton {
                    click()
                }
            }

            AddTaskScreen {
                title {
                    replaceText("$TEST_TASK_TITLE $i")
                }
                description {
                    replaceText(TEST_TASK_DESCRIPTION)
                }
                saveButton {
                    click()
                }
            }
        }
    }

    // sets completed odd tasks
    private fun setCompleted() {

        for (i in 0 until TaskListScreen.taskListView.getSize()) {
            TaskListScreen {
                taskListView {
                    childAt<TaskListScreen.TaskListItem>(i) {
                        checkBox {
                            if (i % 2 == 0) {
                                click()
                            }
                        }
                    }
                }
            }
        }
    }
}