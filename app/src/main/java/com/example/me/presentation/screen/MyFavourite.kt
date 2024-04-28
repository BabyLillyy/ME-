package com.example.me.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.me.R
import com.example.me.presentation.theme.PurpleGrey40
import com.example.me.presentation.theme.anton
import com.example.me.presentation.theme.inter15

@Preview(showBackground = true)
@Composable
fun MyFavourite() {
	val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 8, 9, 9, 9, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 8, 9, 9, 9)

	Column(modifier = Modifier.fillMaxSize()) {

		LazyColumn {
			items(items = list) {
				FavouriteItem()
			}
		}
	}
}

@Composable
fun FavouriteItem() {
	Row(modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
		Image(
			painter = painterResource(R.drawable.person),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.size(25.dp)
				.clip(CircleShape)
				.border(BorderStroke(2.dp, PurpleGrey40), shape = CircleShape)
		)
		Spacer(modifier = Modifier.width(10.dp))
		Text(
			text = "Lilly Kim",
			style = TextStyle(fontFamily = anton, fontWeight = FontWeight.ExtraBold)
		)
		Spacer(modifier = Modifier.width(5.dp))
		Text(text = "likes your post!", style = inter15)

		Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
			Image(
				painter = painterResource(R.drawable.person),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.width(25.dp)
					.height(40.dp)
			)
		}
	}
}