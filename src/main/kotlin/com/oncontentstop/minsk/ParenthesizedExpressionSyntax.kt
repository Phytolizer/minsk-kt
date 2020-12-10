package com.oncontentstop.minsk

class ParenthesizedExpressionSyntax(
    val openParenthesisToken: SyntaxToken,
    val expression: ExpressionSyntax,
    val closeParenthesisToken: SyntaxToken
) : ExpressionSyntax() {
    override val kind: SyntaxKind
        get() = SyntaxKind.ParenthesizedExpression
    override val children: Sequence<SyntaxNode>
        get() = sequenceOf(
            openParenthesisToken,
            expression,
            closeParenthesisToken
        )
}