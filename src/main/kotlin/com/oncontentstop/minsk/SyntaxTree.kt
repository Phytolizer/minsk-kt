package com.oncontentstop.minsk

class SyntaxTree(
    val errorReporter: ErrorReporter,
    val root: ExpressionSyntax,
    val endOfFileToken: SyntaxToken
) {
    companion object {
        fun parse(text: String, errorReporter: ErrorReporter): SyntaxTree {
            val parser = Parser(text, errorReporter)
            return parser.parse()
        }
    }
}