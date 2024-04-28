package com.example.me.presentation.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.me.presentation.theme.PinkBrown
import com.example.me.presentation.theme.anton
import com.example.me.presentation.theme.anton15
import com.example.me.presentation.theme.anton40
import com.example.me.presentation.theme.inter10
import com.example.me.presentation.theme.lightBlue
import com.example.me.presentation.theme.lightGreen
import com.example.me.presentation.theme.lightPink
import com.example.me.presentation.theme.lightPurple
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(onLoginClicked: (() -> Unit)? = null) {
	val isRegistered = remember { mutableStateOf(false) }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		LogoText()
		RegisterContent(isRegistered, onLoginClicked)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBox(field: String = "", state: MutableState<String>) {
	OutlinedTextField(
		value = state.value,
		onValueChange = { state.value = it },
		label = { Text(text = field) },
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp)
	)
}


@Composable
fun RegisterContent(isRegistered: MutableState<Boolean>, onLoginClicked: (() -> Unit)?) {
	val email = remember { mutableStateOf("") }
	val username = remember { mutableStateOf("") }
	val password = remember { mutableStateOf("") }
	val isEmailVisible = remember { mutableStateOf(false) }
	val isUsernameVisible = remember { mutableStateOf(false) }
	val isPassVisible = remember { mutableStateOf(false) }
	val isButtonVisible = remember { mutableStateOf(false) }

	LaunchedEffect(Unit) {
		delay(1.seconds)
		isUsernameVisible.value = true
		delay(1.seconds)
		isPassVisible.value = true
		delay(1.seconds)
		isButtonVisible.value = true
	}

	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		if (isEmailVisible.value) if (isRegistered.value) InputBox(field = "Email", state = email)
		if (isUsernameVisible.value) InputBox(field = "Username", state = username)
		if (isPassVisible.value) InputBox(field = "Password", state = password)
		if (isButtonVisible.value) {
			Button(
				modifier = Modifier.padding(top = 10.dp),
				onClick = { onLoginClicked?.invoke() },
				border = BorderStroke(1.dp, Color.Black),
				colors = ButtonDefaults.buttonColors(
					PinkBrown
				)
			) {
				Text(text = "Okay!", color = Color.White, style = anton15)
			}
			Text(
				text = "I didn't have an account yet!!",
				color = PinkBrown,
				style = inter10
			)
		}
	}
}


@Preview
@Composable
fun LogoText() {
	val fontSize = 48.sp
	val currentFontSizePx = with(LocalDensity.current) { fontSize.toPx() }
	val currentFontSizeDoublePx = currentFontSizePx * 2
	val infiniteTransition = rememberInfiniteTransition(label = "text animation")
	val offset by infiniteTransition.animateFloat(
		initialValue = 0f,
		targetValue = currentFontSizeDoublePx,
		animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing)),
		label = "text animate"
	)
	val brush = Brush.linearGradient(
		colors = listOf(Color.Black, PinkBrown, Color.LightGray),
		start = Offset(offset, offset),
		end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
		tileMode = TileMode.Mirror
	)

	Text(
		text = "Me!",
		style = TextStyle(
			fontFamily = anton,
			brush = brush
		),
		fontSize = fontSize,
		textAlign = TextAlign.Center
	)
}
