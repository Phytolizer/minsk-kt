package com.oncontentstop.minsk

data class SyntaxToken(
    override val kind: SyntaxKind,
    val position: Int,
    val text: String,
    val value: Any?
) : SyntaxNode() {
    override val children: Sequence<SyntaxNode>
        get() = emptySequence()
}
