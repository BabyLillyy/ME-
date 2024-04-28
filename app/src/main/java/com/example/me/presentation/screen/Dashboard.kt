package com.example.me.presentation.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.me.R
import com.example.me.presentation.custom.customShadow
import com.example.me.presentation.theme.PurpleGrey40
import com.example.me.presentation.theme.anton
import com.example.me.presentation.theme.anton20
import com.example.me.presentation.theme.anton30
import com.example.me.presentation.theme.inter10
import com.example.me.presentation.theme.inter12
import com.example.me.presentation.theme.lightBlue
import com.example.me.presentation.theme.lightGreen
import com.example.me.presentation.theme.lightPink
import com.example.me.presentation.theme.lightPurple

@Preview
@Composable
fun Dashboard(onItemClick: ((Int) -> Unit)? = null) {
	val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 8, 9, 9, 9)
	LazyColumn(
		modifier = Modifier.background(Color.LightGray.copy(alpha = 0.3f))
	) {
		items(items = list, itemContent = {
			MyCard(
				modifier = Modifier.padding(
					top = 10.dp,
					start = 20.dp,
					end = 20.dp,
					bottom = 10.dp
				)
			) {
				onItemClick?.invoke(it)
			}
		})
	}
}

@Preview
@Composable
fun MyCard(modifier: Modifier = Modifier, onItemClick: (() -> Unit)? = null) {
	Surface(
		modifier = modifier,
		color = Color.Transparent
	) {
		Column {
			Spacer(modifier = Modifier.height(30.dp))
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.clip(RoundedCornerShape(5.dp))
					.background(Color.White)
					.clickable { onItemClick?.invoke() }
					.padding(horizontal = 20.dp, vertical = 15.dp)
			) {

				Spacer(modifier = Modifier.height(170.dp))
				MyName()
				Spacer(modifier = Modifier.height(15.dp))
				Text(
					style = inter12,
					text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Varius morbi enim nunc faucibus a pellentesque. Comm..."
				)
			}
		}
		MyPic(
			modifier = Modifier
				.padding(horizontal = 10.dp, vertical = 5.dp)
				.fillMaxWidth()
				.height(200.dp)
				.rotate(1f)
				.clip(RoundedCornerShape(5.dp))
		)
	}
}

@Composable
fun MyPic(modifier: Modifier = Modifier) {
	Image(
		modifier = modifier,
		contentScale = ContentScale.Crop,
		painter = painterResource(id = R.drawable.person),
		contentDescription = null
	)
}

@Preview(showBackground = true)
@Composable
fun MyName(nameTextStyle: TextStyle = anton20) {
	Column {
		Text(text = "Star SSNK", style = nameTextStyle)
		Row(verticalAlignment = Alignment.CenterVertically) {
			Image(
				painter = painterResource(id = R.drawable.ic_pin),
				contentDescription = null,
				modifier = Modifier.size(10.dp)
			)
			Spacer(modifier = Modifier.width(2.dp))
			Text(text = "Bangkok, Thailand", style = inter10)
			Spacer(modifier = Modifier.width(5.dp))
			Image(
				painter = painterResource(id = R.drawable.ic_heart),
				contentDescription = null,
				colorFilter = ColorFilter.tint(Color.Red),
				modifier = Modifier.size(10.dp)
			)
			Spacer(modifier = Modifier.width(2.dp))
			Text(text = "111K", style = inter10)
		}

	}
}

@Preview(showBackground = true)
@Composable
fun MyPost() {
	Column(modifier = Modifier.fillMaxWidth()) {
		Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
			MyName(anton30)
		}
		Text(
			modifier = Modifier.padding(25.dp),
			style = inter12,
			text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Varius morbi enim nunc faucibus a pellentesque. Comm..."
		)
	}
}

@Composable
fun TriangleDown(modifier: Modifier = Modifier) {
	Canvas(modifier = modifier) {
		val trianglePath = Path().apply {
			moveTo(0f, 0f) // Move to top left corner
			lineTo(size.width, 0f) // Line to top right corner
			lineTo(size.width / 2f, size.height) // Line to bottom center
			close() // Close the path to create a triangle
		}
		drawPath(color = Color.White, path = trianglePath)
	}
}




