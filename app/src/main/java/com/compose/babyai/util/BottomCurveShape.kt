package com.compose.babyai.util

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


class BottomCurveShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, size.height - 80f)

            quadraticBezierTo(
                size.width / 2,
                size.height + 80f,
                size.width,
                size.height - 80f
            )

            lineTo(size.width, 0f)
            close()
        }

        return Outline.Generic(path)
    }
}
