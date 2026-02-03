package com.compose.babyai.util

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dashedBorder(
    color: Color,
    strokeWidth: Dp = 1.dp,
    dashWidth: Dp = 6.dp,
    dashGap: Dp = 4.dp,
    shape: Shape = RoundedCornerShape(0.dp)
): Modifier = this.then(
    Modifier.drawBehind {
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashWidth.toPx(), dashGap.toPx())
            )
        )

        val outline = shape.createOutline(size, layoutDirection, this)
        drawOutline(
            outline = outline,
            color = color,
            style = stroke
        )
    }
)