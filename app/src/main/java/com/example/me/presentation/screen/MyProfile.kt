package com.example.me.presentation.screen

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.me.presentation.MyProfilePic
import com.example.me.presentation.data.TabItem
import com.example.me.presentation.theme.anton15
import com.example.me.presentation.theme.anton30
import com.example.me.presentation.theme.inter12
import kotlinx.coroutines.launch

val tabs = listOf(
	TabItem(
		title = "Posts",
		value = "20",
		screen = {
			Text(text = "Post")
		}
	),
	TabItem(
		title = "Followers",
		value = "990K",
		screen = {
			Text(text = "Followers")
		}
	),
	TabItem(
		title = "Following",
		value = "114K",
		screen = {
			Text(text = "Following")

		}
	)
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview
@Composable
fun MyProfile() {
	val scrollState = rememberScrollState()
	val coroutineScope = rememberCoroutineScope()
	val pagerState =
		rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) { tabs.size }
	val selectedIndex = animateIntAsState(targetValue = pagerState.currentPage, label = "selection")

	Scaffold(
		modifier = Modifier.background(Color.White),
//			.verticalScroll(state = scrollState)

		topBar = {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.background(Color.White)
			) {
				Spacer(modifier = Modifier.height(30.dp))
				MyProfilePic(
					modifier = Modifier
						.size(130.dp)
						.clip(CircleShape)
				)
				Spacer(modifier = Modifier.height(10.dp))
				Text(text = "Star SSNK", style = anton30)
				Spacer(modifier = Modifier.height(5.dp))
				Text(
					modifier = Modifier.padding(horizontal = 20.dp),
					textAlign = TextAlign.Center,
					style = inter12,
					text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Varius morbi enim nunc faucibus a pellentesque. Comm..."
				)
				Spacer(modifier = Modifier.height(25.dp))
			}
		}
	) {
		Column(
			modifier = Modifier.padding(it)
		) {
			TabRow(selectedTabIndex = selectedIndex.value) {
				tabs.forEachIndexed { index, item ->
					Tab(
						selected = index == pagerState.currentPage,
						text = {
							Column(horizontalAlignment = Alignment.CenterHorizontally) {
								Text(text = item.value, style = anton15)
								Text(
									textAlign = TextAlign.Center,
									style = inter12,
									text = item.title
								)
							}
						},
						onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
					)
				}
			}

			HorizontalPager(state = pagerState) {
				tabs[pagerState.currentPage].screen()
			}
		}
	}
}