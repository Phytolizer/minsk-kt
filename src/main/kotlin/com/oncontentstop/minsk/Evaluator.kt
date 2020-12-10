package com.oncontentstop.minsk

class Evaluator(private val root: ExpressionSyntax) {
    fun evaluate(): Int {
        return evaluateExpression(root)
    }

    fun evaluateExpression(root: ExpressionSyntax): Int {
        when (root.kind) {
            SyntaxKind.BinaryExpression -> {
                return evaluateBinaryExpression(root as BinaryExpressionSyntax)
            }
            SyntaxKind.LiteralExpression -> {
                return evaluateLiteralExpression(root as LiteralExpressionSyntax)
            }
            else -> throw Error("Unexpected node kind ${root.kind}")
        }
    }

    private fun evaluateLiteralExpression(root: LiteralExpressionSyntax): Int {
        return root.literalToken.value as Int
    }

    private fun evaluateBinaryExpression(root: BinaryExpressionSyntax): Int {
        val left = evaluateExpression(root.left)
        val right = evaluateExpression(root.right)

        return when (root.operatorToken.kind) {
            SyntaxKind.PlusToken -> left + right
            SyntaxKind.MinusToken -> left - right
            SyntaxKind.StarToken -> left * right
            SyntaxKind.SlashToken -> left / right
            else -> throw Error("Unexpected binary operator ${root.operatorToken.kind}")
        }
    }
}