package com.oncontentstop.minsk

import com.shakram02.colors.ConsoleColors

fun main() {
    while (true) {
        print("minsk" + ConsoleColors.GREEN + ">>" + ConsoleColors.RESET + " ")
        val line = readLine()
        if (line.isNullOrEmpty()) {
            break
        }

        val lexer = Lexer(line)
        while (true) {
            val token = lexer.lex()
            print("${token.kind}: '${token.text}'")
            if (token.value != null) {
                print(" ${token.value}")
            }
            println()

            if (token.kind == SyntaxKind.EndOfFileToken) {
                break
            }
        }
    }
}

