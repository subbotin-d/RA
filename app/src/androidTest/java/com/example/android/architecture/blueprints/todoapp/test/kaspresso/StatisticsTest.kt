package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.espresso.TodoListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.AddToDoScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.NavigationDrawer
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.StatisticsScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class StatisticsTest: TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    // TODO Проверить что статистика отображается корректно, в соответствии
    //  с завершенными/незавершенными
    @Test
    fun checkStatistics() =
        before {
            ToDoListScreen {
                addToDoButton {
                    click()
                }
            }

            AddToDoScreen {
                title {
                    replaceText("New todo")
                }

                description {
                    replaceText("New description")
                }

                saveButton {
                    click()
                }
            }

            ToDoListScreen.todos.childAt<ToDoListScreen.TodoItem>(2) {
                todoCheckBox {
                    click()
                }
            }

        }.after {
            //Do nothing here
        }.run {
            NavigationDrawer {
                burgerMenu {
                    open()
                }

                statisticsButton {
                    click()
                }
            }

            StatisticsScreen {
                activeTodos {
                    hasText("Active tasks: 66,7%")
                }

                completedTodos {
                    hasText("Completed tasks: 33,3%")
                }
            }
        }
}
