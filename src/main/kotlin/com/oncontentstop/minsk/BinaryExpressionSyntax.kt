package com.oncontentstop.minsk

class BinaryExpressionSyntax(
    val left: ExpressionSyntax,
    val operatorToken: SyntaxToken,
    val right: ExpressionSyntax
) : ExpressionSyntax() {
    override val kind: SyntaxKind
        get() = SyntaxKind.BinaryExpression
    override val children: Sequence<SyntaxNode>
        get() = sequenceOf(left, operatorToken, right)
}
