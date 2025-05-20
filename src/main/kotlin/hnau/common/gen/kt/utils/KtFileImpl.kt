package hnau.common.gen.kt.utils

import hnau.common.gen.kt.Importable
import hnau.common.gen.kt.KtFile

@PublishedApi
internal class KtFileImpl(
    private val onNewLine: (Line) -> Unit,
    private val onNewImport: (Importable) -> Unit,
) : KtFile {

    data class Line(
        val indents: Int,
        val text: String,
    )

    override operator fun String.unaryPlus() {
        onNewLine(
            Line(
                indents = 0,
                text = this,
            )
        )
    }

    override fun addImport(
        importable: Importable,
    ) {
        onNewImport(importable)
    }

    override fun indent(
        block: KtFile.() -> Unit,
    ) {
        val indented: KtFile = KtFileImpl(
            onNewLine = { indentedLine ->
                onNewLine(
                    indentedLine.copy(
                        indents = indentedLine.indents + 1,
                    )
                )
            },
            onNewImport = onNewImport,
        )
        indented.block()
    }
}