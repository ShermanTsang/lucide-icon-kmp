package com.shermant.generator.writer

import com.shermant.generator.toPascalCaseIdentifier
import com.shermant.generator.model.PaintToken
import com.shermant.generator.model.ParsedIcon
import com.shermant.generator.model.StrokeCapToken
import com.shermant.generator.model.StrokeJoinToken
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

class IconFileWriter {
    fun write(icon: ParsedIcon, outputDirectory: Path, packageName: String): Path {
        outputDirectory.createDirectories()
        val fileName = icon.name.toPascalCaseIdentifier() + ".kt"
        val target = outputDirectory.resolve(fileName)
        target.writeText(buildFile(icon, packageName))
        return target
    }

    private fun buildFile(icon: ParsedIcon, packageName: String): String {
        val iconIdentifier = icon.name.toPascalCaseIdentifier()
        return buildString {
            appendLine("package $packageName")
            appendLine()
            appendLine("import androidx.compose.ui.graphics.Color")
            appendLine("import androidx.compose.ui.graphics.PathFillType")
            appendLine("import androidx.compose.ui.graphics.SolidColor")
            appendLine("import androidx.compose.ui.graphics.StrokeCap")
            appendLine("import androidx.compose.ui.graphics.StrokeJoin")
            appendLine("import androidx.compose.ui.graphics.vector.ImageVector")
            appendLine("import androidx.compose.ui.graphics.vector.PathParser")
            appendLine("import androidx.compose.ui.unit.dp")
            appendLine("import com.shermant.core.registry.IconRenderParameters")
            appendLine("import com.shermant.core.registry.ParameterizedIconProvider")
            appendLine()
            appendLine("val ${icon.name.toPascalCaseIdentifier().replaceFirstChar(Char::lowercaseChar)}Provider = ParameterizedIconProvider { parameters ->")
            appendLine("    build$iconIdentifier(parameters)")
            appendLine("}")
            appendLine()
            icon.paths.forEachIndexed { index, path ->
                appendLine(
                    "private val ${icon.name.toPascalCaseIdentifier().replaceFirstChar(Char::lowercaseChar)}Path$index = " +
                        "PathParser().parsePathString(${path.pathData.asKotlinStringLiteral()}).toNodes()",
                )
            }
            appendLine()
            appendLine("private fun build$iconIdentifier(parameters: IconRenderParameters = IconRenderParameters()): ImageVector {")
            appendLine("    val iconSize = (parameters.size ?: ${icon.viewportWidth}f).dp")
            appendLine("    val strokeWidth = parameters.strokeWidth ?: ${icon.defaultStrokeWidth}f")
            appendLine()
            appendLine("    return ImageVector.Builder(")
            appendLine("        name = ${icon.name.asKotlinStringLiteral()},")
            appendLine("        defaultWidth = iconSize,")
            appendLine("        defaultHeight = iconSize,")
            appendLine("        viewportWidth = ${icon.viewportWidth}f,")
            appendLine("        viewportHeight = ${icon.viewportHeight}f,")
            appendLine("    ).apply {")
            icon.paths.forEachIndexed { index, path ->
                appendLine("        addPath(")
                appendLine("            pathData = ${icon.name.toPascalCaseIdentifier().replaceFirstChar(Char::lowercaseChar)}Path$index,")
                appendLine("            pathFillType = PathFillType.NonZero,")
                appendLine("            fill = ${path.fill.toBrushExpression()},")
                appendLine("            stroke = ${path.stroke.toBrushExpression()},")
                appendLine("            strokeLineWidth = ${if (path.stroke == PaintToken.None) "0f" else "strokeWidth"}${if (path.stroke != PaintToken.None && path.strokeWidth != icon.defaultStrokeWidth) " * ${path.strokeWidth / icon.defaultStrokeWidth}f" else ""},")
                appendLine("            strokeLineCap = ${path.strokeLineCap.toComposeExpression()},")
                appendLine("            strokeLineJoin = ${path.strokeLineJoin.toComposeExpression()},")
                appendLine("        )")
            }
            appendLine("    }.build()")
            appendLine("}")
        }
    }

    private fun PaintToken.toBrushExpression(): String = when (this) {
        PaintToken.None -> "null"
        PaintToken.CurrentColor -> "SolidColor(Color.Black)"
    }

    private fun StrokeCapToken.toComposeExpression(): String = when (this) {
        StrokeCapToken.Butt -> "StrokeCap.Butt"
        StrokeCapToken.Round -> "StrokeCap.Round"
        StrokeCapToken.Square -> "StrokeCap.Square"
    }

    private fun StrokeJoinToken.toComposeExpression(): String = when (this) {
        StrokeJoinToken.Bevel -> "StrokeJoin.Bevel"
        StrokeJoinToken.Miter -> "StrokeJoin.Miter"
        StrokeJoinToken.Round -> "StrokeJoin.Round"
    }

    private fun String.asKotlinStringLiteral(): String = buildString {
        append('"')
        for (character in this@asKotlinStringLiteral) {
            when (character) {
                '\\' -> append("\\\\")
                '"' -> append("\\\"")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                else -> append(character)
            }
        }
        append('"')
    }
}
