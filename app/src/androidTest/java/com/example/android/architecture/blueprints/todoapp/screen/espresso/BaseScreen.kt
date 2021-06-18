package com.example.android.architecture.blueprints.todoapp.screen.espresso

abstract class BaseScreen<T> {

	operator fun invoke(lambda: T.() -> Unit) {
		lambda.invoke(this as T)
	}
}