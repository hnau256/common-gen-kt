package hnau.common.gen.kt

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSAnnotation
import hnau.common.kotlin.foldNullable
import hnau.common.kotlin.ifNull

class AnnotationArguments(
    @PublishedApi
    internal val annotation: KSAnnotation,
    @PublishedApi
    internal val logger: KSPLogger,
) {

    @PublishedApi
    internal val arguments: Map<String, Any> = annotation
        .arguments
        .associate { argument ->
            val name = argument.name!!.asString()
            val value = argument.value!!
            name to value
        }

    inline fun <reified T : Any> get(
        name: String,
    ): T? = arguments[name].foldNullable(
        ifNull = {
            logger.error("There is no argument with name '$name'", annotation)
            null
        },
        ifNotNull = { value ->
            (value as? T).ifNull {
                logger.error(
                    "Expected type of argument '$name' is ${T::class}, actual is ${value.javaClass}",
                    annotation
                )
                null
            }
        }
    )
}

fun KSAnnotation.arguments(
    logger: KSPLogger,
): AnnotationArguments = AnnotationArguments(
    annotation = this,
    logger = logger,
)