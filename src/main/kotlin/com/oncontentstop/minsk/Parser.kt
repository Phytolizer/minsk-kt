package com.oncontentstop.minsk

class Parser(text: String, private val errorReporter: ErrorReporter) {
    private var position = 0
    private val tokens: List<SyntaxToken>

    init {
        val lexer = Lexer(text, errorReporter)
        val tempTokens = mutableListOf<SyntaxToken>()
        while (true) {
            val token = lexer.lex()
            if (token.kind != SyntaxKind.WhitespaceToken && token.kind != SyntaxKind.BadToken) {
                tempTokens.add(token)
            }
            if (token.kind == SyntaxKind.EndOfFileToken) {
                break
            }
        }
        tokens = tempTokens
    }

    fun parse(): SyntaxTree {
        val expression = parseExpression()
        val endOfFileToken = matchToken(SyntaxKind.EndOfFileToken)
        return SyntaxTree(errorReporter, expression, endOfFileToken)
    }

    private fun parseExpression(): ExpressionSyntax {
        return parseTerm()
    }

    private fun parseTerm(): ExpressionSyntax {
        var left = parseFactor()

        while (current.kind in sequenceOf(
                SyntaxKind.PlusToken,
                SyntaxKind.MinusToken
            )
        ) {
            val operatorToken = nextToken()
            val right = parseFactor()

            left = BinaryExpressionSyntax(left, operatorToken, right)
        }

        return left
    }

    private fun parseFactor(): ExpressionSyntax {
        var left = parsePrimaryExpression()

        while (current.kind in sequenceOf(
                SyntaxKind.StarToken,
                SyntaxKind.SlashToken
            )
        ) {
            val operatorToken = nextToken()
            val right = parsePrimaryExpression()

            left = BinaryExpressionSyntax(left, operatorToken, right)
        }

        return left
    }

    private fun parsePrimaryExpression(): ExpressionSyntax {
        val literalToken = matchToken(SyntaxKind.NumberToken)
        return LiteralExpressionSyntax(literalToken)
    }

    private fun matchToken(kind: SyntaxKind): SyntaxToken {
        if (current.kind == kind) {
            return nextToken()
        }

        errorReporter.report("Unexpected token <${current.kind}>, expected <$kind>")
        return SyntaxToken(kind, current.position, "", null)
    }

    private fun peek(offset: Int): SyntaxToken {
        val index = position + offset
        if (index >= tokens.size) {
            return tokens.last()
        }
        return tokens[index]
    }

    private val current
        get() = peek(0)

    private fun nextToken(): SyntaxToken {
        val c = current
        ++position
        return c
    }
}

