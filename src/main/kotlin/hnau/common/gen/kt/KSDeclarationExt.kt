package hnau.common.gen.kt

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSName
import hnau.common.kotlin.ifNull

fun KSDeclaration.getQualifiedName(
    logger: KSPLogger,
): KSName? = qualifiedName.ifNull {
    logger.error("Unable get qualified name", this)
    null
}

fun KSDeclaration.nameWithoutPackage(
    logger: KSPLogger,
): String? {
    val fullName = getQualifiedName(logger)?.asString() ?: return null
    val packageName = packageName.asString()
    return fullName.removePrefix("$packageName.")
}
