package com.example.me.presentation.custom

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val defaultShadow = ShadowProperty(color = Color.LightGray, borderRadius = 5.dp, blurRadius = 10.dp)

fun Modifier.customShadow(
	shadowProperty: ShadowProperty = defaultShadow
) = this.then(
	shadowProperty.modifier.drawBehind {
		this.drawIntoCanvas {
			val paint = Paint()
			val frameworkPaint = paint.asFrameworkPaint()
			val spreadPixel = shadowProperty.spread.toPx()
			val leftPixel = (0f - spreadPixel) + shadowProperty.offsetX.toPx()
			val topPixel = (0f - spreadPixel) + shadowProperty.offsetY.toPx()
			val rightPixel = (this.size.width + spreadPixel)
			val bottomPixel = (this.size.height + spreadPixel)

			if (shadowProperty.blurRadius != 0.dp) {
				frameworkPaint.maskFilter =
					(BlurMaskFilter(shadowProperty.blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
			}

			frameworkPaint.color = shadowProperty.color.toArgb()
			it.drawRoundRect(
				left = leftPixel,
				top = topPixel,
				right = rightPixel,
				bottom = bottomPixel,
				radiusX = shadowProperty.borderRadius.toPx(),
				radiusY = shadowProperty.borderRadius.toPx(),
				paint
			)
		}
	}
)

data class ShadowProperty(
	val color: Color = Color.Black,
	val borderRadius: Dp = 0.dp,
	val blurRadius: Dp = 0.dp,
	val offsetY: Dp = 0.dp,
	val offsetX: Dp = 0.dp,
	val spread: Dp = 0f.dp,
	val modifier: Modifier = Modifier
)