package com.example.me.presentation.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.me.R
import com.example.me.presentation.theme.anton20
import com.example.me.presentation.utils.Screen
import com.example.me.presentation.utils.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyEditPost(navController: NavHostController? = null, image: Uri? = null) {
	val postText = remember { mutableStateOf("") }
	val keyboardController = LocalSoftwareKeyboardController.current
	val editTextFocus = LocalFocusManager.current

	Box(modifier = Modifier.noRippleClickable {
		keyboardController?.hide()
		editTextFocus.clearFocus()
	}) {
		Column(modifier = Modifier.fillMaxSize()) {
			Image(
				modifier = Modifier
					.fillMaxHeight(0.6f)
					.fillMaxWidth(),
				painter = rememberAsyncImagePainter(image),
				contentDescription = null,
				contentScale = ContentScale.Crop
			)
			OutlinedTextField(
				value = postText.value,
				onValueChange = { postText.value = it },
				label = { Text("Write your caption") },
				keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
				keyboardActions = KeyboardActions(
					onDone = {keyboardController?.hide()}
				),
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight(0.4f)
					.padding(10.dp)
			)

			Spacer(
				modifier = Modifier
					.height(1.dp)
					.fillMaxWidth()
					.background(Color.LightGray)
					.padding(10.dp)
			)

			Spacer(modifier = Modifier.height(20.dp))

			TextWithIcon(R.drawable.ic_pin, "Add your location") {
				navController?.navigate(Screen.Maps.name)
			}
			Spacer(modifier = Modifier.height(20.dp))
			TextWithIcon(R.drawable.ic_music, "Add your music")
		}
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(10.dp),
			contentAlignment = Alignment.BottomCenter
		) {
			OutlinedButton(onClick = { navController?.popBackStack() }) { Text("Ok!", style = anton20) }
		}
	}
}

@Preview
@Composable
fun TextWithIcon(icon: Int = 0, name: String = "", onClick: (() -> Unit?)? = null) {
	Row(
		modifier = Modifier
			.padding(horizontal = 10.dp)
			.clickable { onClick?.invoke() },
		verticalAlignment = Alignment.CenterVertically
	) {
		Image(
			painter = painterResource(id = icon),
			contentDescription = null,
			modifier = Modifier.size(15.dp)
		)
		Spacer(modifier = Modifier.width(10.dp))
		Text(text = name)
	}
}
