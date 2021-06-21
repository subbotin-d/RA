package com.example.android.architecture.blueprints.todoapp.test.kaspresso

import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.agoda.kakao.text.KTextView
import com.example.android.architecture.blueprints.todoapp.screen.kaspresso.*
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import java.lang.Math.pow
import java.lang.Math.round

class TodoTests : TestCase() {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<TasksActivity>(TasksActivity::class.java)

    @Test
    fun checkAddNewTodo() {
        val countBefore = TodoListScreen.todoList.getSize()
        val newItemTitle = "My first kaspresso note title"
        val newItemDescription = "My first kaspresso note description"

        createTodo(newItemTitle, newItemDescription)

        TodoListScreen {
            todoList.childAt<TodoListScreen.TodoListItem>(countBefore) {
                titleLabel.containsText(newItemTitle)
            }

            todoList.hasSize(countBefore + 1)
        }
    }

    @Test
    fun checkRemoveLastTodo() {
        val countBefore = TodoListScreen.todoList.getSize()
        if(countBefore <= 0)
            throw Exception("List has no items to remove")

        var removingItemTitle : String = ""

        TodoListScreen.todoList.childAt<TodoListScreen.TodoListItem>(countBefore - 1) {
            click()
        }

        TodoDetailsScreen {
            removingItemTitle = titleLabel.getText()
            deleteButton.click()
        }

        TodoListScreen {
            todoList.hasSize(countBefore - 1)
            todoList.children<TodoListScreen.TodoListItem> {
                titleLabel.hasNoText(removingItemTitle)
            }
        }
    }

    @Test
    fun checkCompletedTodoFilter() {
        TodoListScreen {
            var countAll : Int = 0
            countAll = todoList.getSize()
            if(countAll < 1)
                throw Exception("Not enough data to perform test")

            var completedTodoTitle : String = ""
            todoList.firstChild<TodoListScreen.TodoListItem> {
                completedTodoTitle = titleLabel.getText()
                doneCheckbox.click()
                doneCheckbox.isChecked()
            }

            filterMenuButton.click()
            filterMenuCompletedButton.click()

            todoList.hasSize(1)
            todoList.children<TodoListScreen.TodoListItem> {
                titleLabel.hasText(completedTodoTitle)
            }
        }
    }

    @Test
    fun checkCompletedTodoStatistics() {
        var countAll = 0
        var completedCount = 0

        createTodo("test title", "test description")

        TodoListScreen {
            countAll = todoList.getSize()
            if (countAll < 2)
                throw Exception("Not enough data to perform test")

            todoList.firstChild<TodoListScreen.TodoListItem> {
                doneCheckbox.click()
                doneCheckbox.isChecked()
            }

            completedCount = 1
        }

        SideDrawerScreen {
            showStatisticsScreen()
        }

        StatisticsScreen {
            activeTasksLabel.containsText("${(100.0 * (countAll - completedCount) / countAll).truncate(1)}%")
            completedTodoLabel.containsText("${(100.0 * completedCount / countAll).truncate(1)}%")
        }
    }

    private fun createTodo(title: String, description: String) {
        TodoListScreen {
            addTodoFab.click()
        }

        AddTodoScreen {
            titleInput.replaceText(title)
            descriptionInput.replaceText(description)
            saveFab.click()
        }
    }

    private fun Double.truncate(digits : Int) : Double {
        val mult = pow(10.0, digits.toDouble())
        return round(this * mult) / mult
    }

    fun KTextView.getText() : String {
        val textRequestAction = GetTextAction()
        view.perform(textRequestAction)
        return textRequestAction.textValue
    }

    class GetTextAction : ViewAction {

        var textValue : String = ""

        override fun getConstraints(): Matcher<View> =
                ViewMatchers.isAssignableFrom(TextView::class.java)

        override fun perform(uiController: UiController?, view: View?) {
            val tv = view as TextView
            textValue = tv.text.toString()
        }

        override fun getDescription(): String = "getting text from a TextView"
    }
}


