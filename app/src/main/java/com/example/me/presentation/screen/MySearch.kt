package com.example.me.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.me.R
import com.example.me.presentation.theme.anton20
import com.example.me.presentation.theme.inter10

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MySearch() {
	val searchInput = remember { mutableStateOf("") }
	val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

	Column(
		modifier = Modifier.fillMaxSize()
	) {

		Spacer(modifier = Modifier.height(10.dp))

		OutlinedTextField(
			value = searchInput.value,
			onValueChange = { searchInput.value = it },
			shape = RoundedCornerShape(30.dp),
			leadingIcon = {
				Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = null)
			},
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 20.dp)
		)

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 10.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Image(
				painter = painterResource(id = R.drawable.person),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier

					.size(150.dp)
					.clip(CircleShape)
			)
			Text(text = "Star SSNK", style = anton20)
			Text(
				text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do",
				style = inter10,
				textAlign = TextAlign.Center
			)
		}

		LazyVerticalGrid(columns = GridCells.Fixed(2)) {
			items(list) { item ->
				MyPic(
					modifier = Modifier
						.padding(2.dp)
						.fillMaxWidth()
						.height(200.dp)
				)
			}
		}
	}

}