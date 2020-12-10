package com.oncontentstop.minsk

enum class SyntaxKind {
    BadToken,
    EndOfFileToken,

    NumberToken,
    WhitespaceToken,
    PlusToken,
    MinusToken,
    StarToken,
    SlashToken,
    OpenParenthesisToken,
    CloseParenthesisToken,

    LiteralExpression,
    BinaryExpression,
    ParenthesizedExpression
}
