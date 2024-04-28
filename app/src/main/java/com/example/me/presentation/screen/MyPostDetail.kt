package com.example.me.presentation.screen

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.me.R
import com.example.me.presentation.custom.customShadow
import com.example.me.presentation.theme.anton15
import com.example.me.presentation.theme.inter10
import com.example.me.presentation.theme.inter15
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyPostDetailScreen(index: String? = "", onNavBack: (() -> Unit)? = null) {
	val isBottomSheetVisible = remember { mutableStateOf(false) }

	Scaffold(topBar = {
		BackArrow(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 15.dp, vertical = 10.dp),
			onNavBack
		)
	}, bottomBar = {
		Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
			Image(
				modifier = Modifier
					.size(50.dp)
					.clickable {
						isBottomSheetVisible.value = !isBottomSheetVisible.value
						println("Lilly - ${isBottomSheetVisible.value}")
					},
				painter = painterResource(id = R.drawable.ic_arrow_up),
				contentDescription = null,
			)
		}

	}) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
				.padding(it)
		) {

			MusicPlayer()
			Image(
				modifier = Modifier
					.fillMaxSize()
					.padding(top = 20.dp)
					.customShadow(),
				contentScale = ContentScale.Crop,
				painter = painterResource(id = R.drawable.person),
				contentDescription = null
			)
			MyPost()
		}
	}

	BottomSheet(isBottomSheetVisible)
}

@Preview(showBackground = true)
@Composable
fun MyComments() {
	val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxSize()
			.customShadow()
			.clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
			.background(Color.White)
			.padding(top = 16.dp, start = 16.dp, end = 16.dp)
	) {
		Spacer(
			modifier = Modifier
				.height(4.dp)
				.fillMaxWidth(0.5f)
				.background(Color.LightGray)
				.clip(CircleShape)
		)

		Spacer(modifier = Modifier.height(10.dp))

		LazyColumn {
			items(items = list) { index ->
				val isLastItem = index == list.lastIndex + 1
				Column {
					MyCommentBox(isLastItem)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MyCommentBox(isInputComment: Boolean = false) {
	val inputComment = remember { mutableStateOf("") }

	Row {
		if (!isInputComment) {
			Image(
				painter = painterResource(id = R.drawable.person),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.clip(CircleShape)
					.size(50.dp)
			)
		}
		Spacer(modifier = Modifier.width(10.dp))
		Column {
			Text(text = if (isInputComment) "You" else "Sammy Kim", style = anton15)
			Spacer(modifier = Modifier.height(5.dp))
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.wrapContentHeight()
					.clip(
						RoundedCornerShape(
							topEnd = 30.dp, bottomStart = 30.dp, bottomEnd = 30.dp
						)
					)
					.background(Color.LightGray)
					.padding(15.dp)
			) {
				if (isInputComment) {
					BasicTextField(
						value = inputComment.value,
						onValueChange = { inputComment.value = it },
						modifier = Modifier.fillMaxWidth(),
					)
					Image(painter = painterResource(id = R.drawable.ic_send),
						contentDescription = null,
						modifier = Modifier
							.align(Alignment.CenterEnd)
							.padding(end = 10.dp)
							.clickable { })
				} else {
					Text(
						text = "When he asked her favorite number, she answered without hesitation that it was diamonds.",
						style = inter15
					)
				}
			}
			Spacer(modifier = Modifier.height(10.dp))
		}
	}
}

@Preview
@Composable
fun BackArrow(modifier: Modifier = Modifier, onNavBack: (() -> Unit)? = null) {
	Box(
		modifier = modifier, contentAlignment = Alignment.CenterStart
	) {
		Image(painter = painterResource(id = R.drawable.ic_back),
			contentDescription = null,
			modifier = Modifier.clickable { onNavBack?.invoke() })
	}
}

@Preview(showBackground = true)
@Composable
fun MusicPlayer() {
	Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
		Text("Daylight - Taylor Swift", style = inter10, fontWeight = FontWeight.ExtraBold)

		Row(verticalAlignment = Alignment.CenterVertically) {
			Image(
				painter = painterResource(id = R.drawable.ic_pause),
				contentDescription = null,
				modifier = Modifier.size(20.dp)
			)
			Spacer(modifier = Modifier.width(5.dp))

			Surface {
				Box(
					modifier = Modifier
						.width(200.dp)
						.background(Color.LightGray)
						.height(2.dp)
				)
				Box(
					modifier = Modifier
						.width(120.dp)
						.background(Color.Black)
						.height(2.dp)
				)
			}
			Spacer(modifier = Modifier.width(5.dp))
		}
	}
}

@Composable
fun BottomSheet(isBottomSheetVisible: MutableState<Boolean>) {
	BottomSheetContent(state = isBottomSheetVisible)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(modifier: Modifier = Modifier, state: MutableState<Boolean>) {
	val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
	val coroutineScope = rememberCoroutineScope()

	ModalBottomSheetLayout(
		sheetState = bottomSheetState,
		sheetContent = {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight(0.5f)
					.background(Color.White)
			) {
				MyComments()
			}
		},
		content = {},
		scrimColor = Color.Black.copy(alpha = 0.5f),
		sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
	)

	if (state.value) {
		coroutineScope.launch {
			bottomSheetState.show()
		}
	}

	if (!bottomSheetState.isVisible) {
		state.value = false
	}
}