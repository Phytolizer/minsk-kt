package com.oncontentstop.minsk

class Lexer(private val text: String) {
    private var position = 0
    private var start = 0
    private var kind = SyntaxKind.BadToken
    private var value: Any? = null

    private val current: Char
        get() {
            if (position >= text.length) {
                return '\u0000'
            }
            return text[position]
        }

    private val tokenText
        get() = text.substring(start until position)

    fun lex(): SyntaxToken {
        start = position
        value = null
        when {
            current.isDigit() -> {
                readNumberToken()
            }
            current.isWhitespace() -> {
                readWhitespaceToken()
            }
            else -> {
                kind = when (current) {
                    '\u0000' -> return SyntaxToken(
                        SyntaxKind.EndOfFileToken,
                        start,
                        "",
                        null
                    )
                    '+' -> SyntaxKind.PlusToken
                    '-' -> SyntaxKind.MinusToken
                    '*' -> SyntaxKind.StarToken
                    '/' -> SyntaxKind.SlashToken
                    '(' -> SyntaxKind.OpenParenthesisToken
                    ')' -> SyntaxKind.CloseParenthesisToken
                    else -> SyntaxKind.BadToken
                }
                ++position
            }
        }

        if (kind == SyntaxKind.BadToken) {
            TODO()
        }
        return SyntaxToken(kind, start, tokenText, value)
    }

    private fun readWhitespaceToken() {
        while (current.isWhitespace()) {
            ++position
        }

        kind = SyntaxKind.WhitespaceToken
    }

    private fun readNumberToken() {
        while (current.isDigit()) {
            ++position
        }

        kind = SyntaxKind.NumberToken
        value = Integer.parseInt(tokenText)
    }
}
