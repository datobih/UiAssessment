package com.example.uiassessment


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun rememberWindowSize(): WindowSizeInfo {
    val configuration = LocalConfiguration.current
    return WindowSizeInfo(
        widthSize = configuration.screenWidthDp.dp,
        heightSize = configuration.screenHeightDp.dp,
        widthInfo = when {
            configuration.screenWidthDp < 600 -> WindowType.Compact
            configuration.screenWidthDp < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        },
        heightInfo = when {
            configuration.screenHeightDp < 480 -> WindowType.Compact
            configuration.screenHeightDp < 900 -> WindowType.Medium
            else -> WindowType.Expanded
        }
    )
}
data class WindowSizeInfo(
    val widthSize: Dp,
    val heightSize: Dp,
    val widthInfo: WindowType,
    val heightInfo: WindowType
)
sealed class WindowType {
    object Compact : WindowType()
    object Medium : WindowType()
    object Expanded : WindowType()
}




@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.topBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) :Modifier{

        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        return Modifier.drawBehind {
            val width = size.width
            val height = size.height

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = 0f, y = cornerRadiusPx),
                strokeWidth = strokeWidthPx
            )

            drawArc(
                color = color,
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset.Zero,
                size = Size(cornerRadiusPx * 2, cornerRadiusPx * 2),
                style = Stroke(width = strokeWidthPx)
            )

            drawLine(
                color = color,
                start = Offset(x = cornerRadiusPx, y = 0f),
                end = Offset(x = width - cornerRadiusPx, y = 0f),
                strokeWidth = strokeWidthPx
            )

            drawArc(
                color = color,
                startAngle = 270f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(x = width - cornerRadiusPx * 2, y = 0f),
                size = Size(cornerRadiusPx * 2, cornerRadiusPx * 2),
                style = Stroke(width = strokeWidthPx)
            )

            drawLine(
                color = color,
                start = Offset(x = width, y = height),
                end = Offset(x = width, y = cornerRadiusPx),
                strokeWidth = strokeWidthPx
            )
        }
    }



@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) :Modifier{

        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        return Modifier.drawBehind {
            val width = size.width
            val height = size.height

            drawLine(
                color = color,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = 0f, y = height-cornerRadiusPx),
                strokeWidth = strokeWidthPx
            )

            drawArc(
                color = color,
                startAngle = 90f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(x = 0f, y = height - cornerRadiusPx * 2),
                size = Size(cornerRadiusPx * 2, cornerRadiusPx * 2),
                style = Stroke(width = strokeWidthPx)
            )

            drawLine(
                color = color,
                start = Offset(x = cornerRadiusPx, y = height),
                end = Offset(x = width - cornerRadiusPx, y = height),
                strokeWidth = strokeWidthPx
            )

            drawArc(
                color = color,
                startAngle = 0f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(x = width - cornerRadiusPx * 2, y = height - cornerRadiusPx * 2),
                size = Size(cornerRadiusPx * 2, cornerRadiusPx * 2),
                style = Stroke(width = strokeWidthPx)
            )

            drawLine(
                color = color,
                start = Offset(x = width, y = 0f),
                end = Offset(x = width, y = height - cornerRadiusPx),
                strokeWidth = strokeWidthPx
            )
        }
    }



@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.sideBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) : Modifier {

        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

       return Modifier.drawBehind {
            val width = size.width
            val height = size.height

            drawLine(
                color = color,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = 0f, y = height),
                strokeWidth = strokeWidthPx
            )

            drawLine(
                color = color,
                start = Offset(x = width, y = 0f),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
