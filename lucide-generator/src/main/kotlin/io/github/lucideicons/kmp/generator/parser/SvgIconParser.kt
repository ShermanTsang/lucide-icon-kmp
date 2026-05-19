package io.github.lucideicons.kmp.generator.parser

import io.github.lucideicons.kmp.generator.toDisplayName
import io.github.lucideicons.kmp.generator.model.PaintToken
import io.github.lucideicons.kmp.generator.model.ParsedIcon
import io.github.lucideicons.kmp.generator.model.ParsedVectorPath
import io.github.lucideicons.kmp.generator.model.RawLucideIcon
import io.github.lucideicons.kmp.generator.model.StrokeCapToken
import io.github.lucideicons.kmp.generator.model.StrokeJoinToken
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Element
import org.xml.sax.InputSource

class SvgIconParser {
    fun parse(icon: RawLucideIcon): ParsedIcon {
        val document = documentBuilderFactory.newDocumentBuilder().parse(InputSource(StringReader(icon.svgContent)))
        val svg = document.documentElement
        require(svg.tagName == "svg") { "SVG '${icon.name}' has an unexpected root element: ${svg.tagName}" }

        val viewportWidth = extractViewportDimension(svg, "width", 2)
        val viewportHeight = extractViewportDimension(svg, "height", 3)
        val inheritedStyle = SvgStyle.fromElement(svg)
        val parsedPaths = mutableListOf<ParsedVectorPath>()

        val children = svg.childNodes
        for (index in 0 until children.length) {
            val node = children.item(index)
            if (node !is Element) {
                continue
            }

            val pathData = when (node.tagName) {
                "path" -> node.requireAttribute("d", icon.name)
                "circle" -> buildCirclePath(node)
                "ellipse" -> buildEllipsePath(node)
                "line" -> buildLinePath(node)
                "polyline" -> buildPolylinePath(node, close = false)
                "polygon" -> buildPolylinePath(node, close = true)
                "rect" -> buildRectPath(node)
                else -> error("Unsupported SVG element '${node.tagName}' in '${icon.name}'.")
            }

            val style = inheritedStyle.merge(node)
            parsedPaths += ParsedVectorPath(
                pathData = pathData,
                fill = style.fill,
                stroke = style.stroke,
                strokeWidth = style.strokeWidth,
                strokeLineCap = style.strokeLineCap,
                strokeLineJoin = style.strokeLineJoin,
            )
        }

        require(parsedPaths.isNotEmpty()) { "SVG '${icon.name}' does not contain any supported vector elements." }

        return ParsedIcon(
            name = icon.name,
            displayName = icon.name.toDisplayName(),
            viewportWidth = viewportWidth,
            viewportHeight = viewportHeight,
            defaultStrokeWidth = inheritedStyle.strokeWidth,
            paths = parsedPaths,
            tags = icon.metadata.tags,
            aliases = icon.metadata.aliases,
            categories = icon.metadata.categories,
        )
    }

    private fun extractViewportDimension(svg: Element, dimensionAttribute: String, viewBoxIndex: Int): Float {
        val attributeValue = svg.getAttribute(dimensionAttribute).trim()
        attributeValue.toFloatOrNull()?.let { return it }

        val viewBox = svg.getAttribute("viewBox").trim()
        require(viewBox.isNotBlank()) { "SVG is missing both '$dimensionAttribute' and viewBox." }
        val parts = viewBox.split(Regex("""\s+"""))
        require(parts.size == 4) { "Unexpected viewBox format: $viewBox" }
        return parts[viewBoxIndex].toFloat()
    }

    private fun buildCirclePath(element: Element): String {
        val cx = element.requireFloatAttribute("cx")
        val cy = element.requireFloatAttribute("cy")
        val radius = element.requireFloatAttribute("r")
        return buildEllipseArcPath(cx = cx, cy = cy, radiusX = radius, radiusY = radius)
    }

    private fun buildEllipsePath(element: Element): String {
        val cx = element.requireFloatAttribute("cx")
        val cy = element.requireFloatAttribute("cy")
        val radiusX = element.requireFloatAttribute("rx")
        val radiusY = element.requireFloatAttribute("ry")
        return buildEllipseArcPath(cx = cx, cy = cy, radiusX = radiusX, radiusY = radiusY)
    }

    private fun buildLinePath(element: Element): String {
        val x1 = element.requireFloatAttribute("x1")
        val y1 = element.requireFloatAttribute("y1")
        val x2 = element.requireFloatAttribute("x2")
        val y2 = element.requireFloatAttribute("y2")
        return "M ${formatNumber(x1)} ${formatNumber(y1)} L ${formatNumber(x2)} ${formatNumber(y2)}"
    }

    private fun buildPolylinePath(element: Element, close: Boolean): String {
        val points = parsePoints(element.requireAttribute("points", "polyline"))
        require(points.size >= 2) { "Polyline/polygon must contain at least two points." }

        return buildString {
            append("M ${formatNumber(points.first().first)} ${formatNumber(points.first().second)}")
            points.drop(1).forEach { (x, y) ->
                append(" L ${formatNumber(x)} ${formatNumber(y)}")
            }
            if (close) {
                append(" Z")
            }
        }
    }

    private fun buildRectPath(element: Element): String {
        val x = element.floatAttributeOrDefault("x")
        val y = element.floatAttributeOrDefault("y")
        val width = element.requireFloatAttribute("width")
        val height = element.requireFloatAttribute("height")
        val rawRx = element.floatAttributeOrNull("rx")
        val rawRy = element.floatAttributeOrNull("ry")
        val radiusX = (rawRx ?: rawRy ?: 0f).coerceAtLeast(0f).coerceAtMost(width / 2f)
        val radiusY = (rawRy ?: rawRx ?: 0f).coerceAtLeast(0f).coerceAtMost(height / 2f)

        if (radiusX == 0f && radiusY == 0f) {
            return buildString {
                append("M ${formatNumber(x)} ${formatNumber(y)}")
                append(" H ${formatNumber(x + width)}")
                append(" V ${formatNumber(y + height)}")
                append(" H ${formatNumber(x)}")
                append(" Z")
            }
        }

        return buildString {
            append("M ${formatNumber(x + radiusX)} ${formatNumber(y)}")
            append(" H ${formatNumber(x + width - radiusX)}")
            append(" A ${formatNumber(radiusX)} ${formatNumber(radiusY)} 0 0 1 ${formatNumber(x + width)} ${formatNumber(y + radiusY)}")
            append(" V ${formatNumber(y + height - radiusY)}")
            append(" A ${formatNumber(radiusX)} ${formatNumber(radiusY)} 0 0 1 ${formatNumber(x + width - radiusX)} ${formatNumber(y + height)}")
            append(" H ${formatNumber(x + radiusX)}")
            append(" A ${formatNumber(radiusX)} ${formatNumber(radiusY)} 0 0 1 ${formatNumber(x)} ${formatNumber(y + height - radiusY)}")
            append(" V ${formatNumber(y + radiusY)}")
            append(" A ${formatNumber(radiusX)} ${formatNumber(radiusY)} 0 0 1 ${formatNumber(x + radiusX)} ${formatNumber(y)}")
            append(" Z")
        }
    }

    private fun buildEllipseArcPath(cx: Float, cy: Float, radiusX: Float, radiusY: Float): String = buildString {
        append("M ${formatNumber(cx + radiusX)} ${formatNumber(cy)}")
        append(" A ${formatNumber(radiusX)} ${formatNumber(radiusY)} 0 1 0 ${formatNumber(cx - radiusX)} ${formatNumber(cy)}")
        append(" A ${formatNumber(radiusX)} ${formatNumber(radiusY)} 0 1 0 ${formatNumber(cx + radiusX)} ${formatNumber(cy)}")
        append(" Z")
    }

    private fun parsePoints(pointsAttribute: String): List<Pair<Float, Float>> {
        val normalized = pointsAttribute
            .replace(',', ' ')
            .trim()
            .split(Regex("""\s+"""))
            .filter { it.isNotBlank() }
            .map { token -> token.toFloat() }

        require(normalized.size % 2 == 0) { "Invalid points attribute: $pointsAttribute" }

        return normalized.chunked(2).map { pair -> pair[0] to pair[1] }
    }

    private fun formatNumber(value: Float): String =
        value.toString().removeSuffix(".0")

    private fun Element.requireAttribute(attributeName: String, iconName: String): String {
        val value = getAttribute(attributeName).trim()
        require(value.isNotBlank()) { "Element '$tagName' in '$iconName' is missing '$attributeName'." }
        return value
    }

    private fun Element.requireFloatAttribute(attributeName: String): Float =
        getAttribute(attributeName).trim().toFloatOrNull()
            ?: error("Element '$tagName' is missing numeric '$attributeName'.")

    private fun Element.floatAttributeOrNull(attributeName: String): Float? =
        getAttribute(attributeName).trim().takeIf { it.isNotBlank() }?.toFloatOrNull()

    private fun Element.floatAttributeOrDefault(attributeName: String, defaultValue: Float = 0f): Float =
        floatAttributeOrNull(attributeName) ?: defaultValue

    private object SvgAttributeSupport {
        fun paintAttributeOrNull(element: Element, attributeName: String): PaintToken? {
            val value = element.getAttribute(attributeName).trim()
            if (value.isBlank()) {
                return null
            }

            return when (value) {
                "none" -> PaintToken.None
                "currentColor" -> PaintToken.CurrentColor
                else -> error("Unsupported $attributeName value '$value' in <${element.tagName}>.")
            }
        }

        fun floatAttributeOrNull(element: Element, attributeName: String): Float? =
            element.getAttribute(attributeName).trim().takeIf { it.isNotBlank() }?.toFloatOrNull()

        fun strokeCapOrNull(element: Element): StrokeCapToken? {
            val value = element.getAttribute("stroke-linecap").trim()
            if (value.isBlank()) {
                return null
            }

            return when (value) {
                "butt" -> StrokeCapToken.Butt
                "round" -> StrokeCapToken.Round
                "square" -> StrokeCapToken.Square
                else -> error("Unsupported stroke-linecap '$value' in <${element.tagName}>.")
            }
        }

        fun strokeJoinOrNull(element: Element): StrokeJoinToken? {
            val value = element.getAttribute("stroke-linejoin").trim()
            if (value.isBlank()) {
                return null
            }

            return when (value) {
                "miter" -> StrokeJoinToken.Miter
                "round" -> StrokeJoinToken.Round
                "bevel" -> StrokeJoinToken.Bevel
                else -> error("Unsupported stroke-linejoin '$value' in <${element.tagName}>.")
            }
        }
    }

    private data class SvgStyle(
        val fill: PaintToken,
        val stroke: PaintToken,
        val strokeWidth: Float,
        val strokeLineCap: StrokeCapToken,
        val strokeLineJoin: StrokeJoinToken,
    ) {
        fun merge(element: Element): SvgStyle = SvgStyle(
            fill = SvgAttributeSupport.paintAttributeOrNull(element, "fill") ?: fill,
            stroke = SvgAttributeSupport.paintAttributeOrNull(element, "stroke") ?: stroke,
            strokeWidth = SvgAttributeSupport.floatAttributeOrNull(element, "stroke-width") ?: strokeWidth,
            strokeLineCap = SvgAttributeSupport.strokeCapOrNull(element) ?: strokeLineCap,
            strokeLineJoin = SvgAttributeSupport.strokeJoinOrNull(element) ?: strokeLineJoin,
        )

        companion object {
            fun fromElement(element: Element): SvgStyle = SvgStyle(
                fill = SvgAttributeSupport.paintAttributeOrNull(element, "fill") ?: PaintToken.None,
                stroke = SvgAttributeSupport.paintAttributeOrNull(element, "stroke") ?: PaintToken.CurrentColor,
                strokeWidth = SvgAttributeSupport.floatAttributeOrNull(element, "stroke-width") ?: 2f,
                strokeLineCap = SvgAttributeSupport.strokeCapOrNull(element) ?: StrokeCapToken.Round,
                strokeLineJoin = SvgAttributeSupport.strokeJoinOrNull(element) ?: StrokeJoinToken.Round,
            )
        }
    }

    private fun Element.paintAttributeOrNull(attributeName: String): PaintToken? {
        val value = getAttribute(attributeName).trim()
        if (value.isBlank()) {
            return null
        }

        return when (value) {
            "none" -> PaintToken.None
            "currentColor" -> PaintToken.CurrentColor
            else -> error("Unsupported $attributeName value '$value' in <$tagName>.")
        }
    }

    private fun Element.strokeCapOrNull(): StrokeCapToken? {
        val value = getAttribute("stroke-linecap").trim()
        if (value.isBlank()) {
            return null
        }

        return when (value) {
            "butt" -> StrokeCapToken.Butt
            "round" -> StrokeCapToken.Round
            "square" -> StrokeCapToken.Square
            else -> error("Unsupported stroke-linecap '$value' in <$tagName>.")
        }
    }

    private fun Element.strokeJoinOrNull(): StrokeJoinToken? {
        val value = getAttribute("stroke-linejoin").trim()
        if (value.isBlank()) {
            return null
        }

        return when (value) {
            "miter" -> StrokeJoinToken.Miter
            "round" -> StrokeJoinToken.Round
            "bevel" -> StrokeJoinToken.Bevel
            else -> error("Unsupported stroke-linejoin '$value' in <$tagName>.")
        }
    }

    private companion object {
        val documentBuilderFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance().apply {
            isNamespaceAware = false
            setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)
            setFeature("http://xml.org/sax/features/external-general-entities", false)
            setFeature("http://xml.org/sax/features/external-parameter-entities", false)
        }
    }
}
