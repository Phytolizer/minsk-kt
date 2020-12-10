package com.oncontentstop.minsk

abstract class SyntaxNode {
    abstract val kind: SyntaxKind
    abstract val children: Sequence<SyntaxNode>
}

