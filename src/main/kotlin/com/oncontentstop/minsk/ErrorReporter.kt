package com.oncontentstop.minsk

abstract class ErrorReporter {
    abstract fun report(message: String)
    abstract val errors: Sequence<String>
}