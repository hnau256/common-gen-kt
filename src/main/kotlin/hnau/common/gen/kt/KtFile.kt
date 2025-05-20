package hnau.common.gen.kt

interface KtFile {

    operator fun String.unaryPlus()

    fun addImport(
        importable: Importable,
    )

    fun indent(
        block: KtFile.() -> Unit,
    )
}