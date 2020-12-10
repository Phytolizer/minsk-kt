package com.oncontentstop.minsk

class Evaluator(private val root: ExpressionSyntax) {
    fun evaluate(): Int {
        return evaluateExpression(root)
    }

    private fun evaluateExpression(root: ExpressionSyntax): Int {
        return when (root.kind) {
            SyntaxKind.BinaryExpression -> evaluateBinaryExpression(root as BinaryExpressionSyntax)
            SyntaxKind.LiteralExpression -> evaluateLiteralExpression(root as LiteralExpressionSyntax)
            SyntaxKind.ParenthesizedExpression -> evaluateParenthesizedExpression(
                root as ParenthesizedExpressionSyntax
            )
            else -> throw Error("Unexpected node kind ${root.kind}")
        }
    }

    private fun evaluateParenthesizedExpression(root: ParenthesizedExpressionSyntax): Int {
        return evaluateExpression(root.expression)
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