package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.android.architecture.blueprints.todoapp.screen.espresso.TodoListScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.AddToDoScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoDetailsScreen
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.ToDoListScreen
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test


class ToDoRemoveTest : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(TasksActivity::class.java)

    //Удалить TODO и проверить что она удалилась
    @Test
    fun checkRemoveToDo() =
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

        }.after {
            //Do nothing here
        }.run {
            TodoListScreen {
                ToDoListScreen.todos.childAt<ToDoListScreen.TodoItem>(2) {

                    todoTitle {
                        click()
                    }
                }
            }

            ToDoDetailsScreen {
                todoDetailTitle {
                    isDisplayed()
                    hasText("New todo")
                }

                todoDetailDescription {
                    isDisplayed()
                    hasText("New description")
                }

                todoDetailCheckBox {
                    isNotChecked()
                }

                deleteToDoButton {
                    click()
                }
            }

            TodoListScreen {
                ToDoListScreen.todos.lastChild<ToDoListScreen.TodoItem> {
                    todoTitle {
                        hasNoText("New todo")
                    }
                }
            }
        }
    }
