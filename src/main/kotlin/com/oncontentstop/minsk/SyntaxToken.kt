package com.oncontentstop.minsk

data class SyntaxToken(
    val kind: SyntaxKind,
    val position: Int,
    val text: String,
    val value: Any?
)
