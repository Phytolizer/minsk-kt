package com.oncontentstop.minsk

class StringErrorReporter : ErrorReporter() {
    private val _errors = mutableListOf<String>()
    override fun report(message: String) {
        _errors.add(message)
    }

    override val errors: Sequence<String>
        get() = _errors.asSequence()
}