package com.example.uiassessment


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
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
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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


fun openCamera(context: Context, uri: Uri, cameraLauncher: ActivityResultLauncher<Uri>) {
    val tempFile = createImageFile(context)

    cameraLauncher.launch(uri) // Launch the camera intent with the URI
}

fun createImageFile(context: Context): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_${timeStamp}_"
    val storageDir = context.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
}



fun createImageUri(context: Context): Uri {
    val imageFile = File.createTempFile("captured_${System.currentTimeMillis()}",".jpg",context.cacheDir)

    return FileProvider.getUriForFile(context,"${context.packageName}.provider",imageFile)
}


fun getFileFromUri(context: Context, uri: Uri?): File? {
    if (uri == null) {
        return null // Handle null Uri gracefully
    }

    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        inputStream?.use { input ->
            val tempFile = createTempFileForUpload(context) // Create a temporary file
            FileOutputStream(tempFile).use { output ->
                input.copyTo(output) // Copy data from InputStream to FileOutputStream
            }
            tempFile // Return the temporary file
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null // Handle exceptions and return null if file creation fails
    }
}

private fun createTempFileForUpload(context: Context): File {
    val timeStamp = System.currentTimeMillis().toString()
    val imageFileName = "UPLOAD_TEMP_IMG_${timeStamp}"
    val storageDir = context.cacheDir // Use app's cache directory for temporary files
    return File.createTempFile(imageFileName, ".jpg", storageDir) // Create .jpg temp file
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
