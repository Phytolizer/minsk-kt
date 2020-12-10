package com.oncontentstop.minsk

import com.shakram02.colors.ConsoleColors

fun main() {
    while (true) {
        print("minsk" + ConsoleColors.GREEN + ">>" + ConsoleColors.RESET + " ")
        val line = readLine()
        if (line.isNullOrEmpty()) {
            break
        }
        val errorReporter = StringErrorReporter()

        val syntaxTree = SyntaxTree.parse(line, errorReporter)

        print(ConsoleColors.WHITE)
        prettyPrint(syntaxTree.root)
        print(ConsoleColors.RESET)
        if (errorReporter.errors.count() > 0) {
            print(ConsoleColors.RED)
            for (error in errorReporter.errors) {
                println(error)
            }
            print(ConsoleColors.RESET)
        } else {
            val evaluator = Evaluator(syntaxTree.root)
            val value = evaluator.evaluate()
            println(value)
        }
    }
}

fun prettyPrint(root: SyntaxNode, indent: String = "", isLast: Boolean = true) {
    print(indent)
    if (isLast) {
        print("\\--")
    } else {
        print("+--")
    }
    print(root.kind)
    if (root is SyntaxToken && root.value != null) {
        print(" ${root.value}")
    }
    println()

    var nextIndent = indent
    nextIndent += if (isLast) {
        "   "
    } else {
        "|  "
    }

    val lastChild = root.children.lastOrNull()

    for (child in root.children) {
        prettyPrint(child, nextIndent, child == lastChild)
    }
}
