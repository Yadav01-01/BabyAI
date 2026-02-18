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
            lineTo(0f, size.height - 80f)  // Left side goes down to -80f

            quadraticBezierTo(
                size.width / 2,
                size.height + 50f,         // Control point (curve depth)
                size.width,
                size.height - 80f          // ← CHANGED: Should match the left side (-80f)
            )

            lineTo(size.width, 0f)
            close()
        }

        return Outline.Generic(path)
    }
}