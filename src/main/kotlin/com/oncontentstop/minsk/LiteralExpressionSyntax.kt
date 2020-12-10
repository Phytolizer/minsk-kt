package com.oncontentstop.minsk

class LiteralExpressionSyntax(val literalToken: SyntaxToken) :
    ExpressionSyntax() {
    override val kind: SyntaxKind
        get() = SyntaxKind.LiteralExpression
    override val children: Sequence<SyntaxNode>
        get() = sequenceOf(literalToken)
}
