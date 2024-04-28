package com.example.me.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.me.R
import com.example.me.presentation.custom.customShadow
import com.example.me.presentation.data.NavigationItem
import com.example.me.presentation.navigation.NavigationHost
import com.example.me.presentation.theme.METheme
import com.example.me.presentation.theme.PinkBrown
import com.example.me.presentation.theme.PurpleGrey40
import com.example.me.presentation.theme.anton
import com.example.me.presentation.utils.Screen
import com.example.me.presentation.utils.createImageFile
import java.util.Objects


class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MainScreen()
		}
	}
}

@Preview
@Composable
fun GreetingPreview() {
	METheme {
		MainScreen()
	}
}

@Composable
fun MainScreen() {
	val navController = rememberNavController()
	val backStackEntry = navController.currentBackStackEntryAsState()
	val isShowTopBar = when (backStackEntry.value?.destination?.route) {
		Screen.Dashboard.name, Screen.Camera.name, Screen.Favourite.name, Screen.Me.name -> true
		else -> false
	}
	val isShowBottomNavBar = when (backStackEntry.value?.destination?.route) {
		Screen.Dashboard.name, Screen.Search.name, Screen.Camera.name, Screen.Favourite.name, Screen.Me.name -> true
		else -> false
	}

	val context = LocalContext.current
	val file = context.createImageFile()
	val uri = FileProvider.getUriForFile(
		Objects.requireNonNull(context),
		context.packageName + ".provider",
		file
	)
	val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
		val encoded = Uri.encode(uri.toString().replace('%', '|'))
		navController.navigate("${Screen.EditPost.name}/$encoded")
	}

	val permissionLauncher =
		rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
			if (it) cameraLauncher.launch(uri)
			else Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
		}

	val permissionCheckResult =
		ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

	Surface(
		modifier = Modifier.fillMaxSize(),
		color = MaterialTheme.colorScheme.background
	) {
		Scaffold(
			topBar = { if (isShowTopBar) ActionBar() },
			bottomBar = {
				if (isShowBottomNavBar) {
					NavigationBottomBar(
						listOf(
							NavigationItem(route = Screen.Dashboard.name, R.drawable.ic_home),
							NavigationItem(route = Screen.Search.name, R.drawable.ic_search),
							NavigationItem(route = Screen.Camera.name, R.drawable.ic_add),
							NavigationItem(route = Screen.Favourite.name, R.drawable.ic_heart),
							NavigationItem(route = Screen.Me.name),
						),
						onItemClick = {
							when (it.route) {
								Screen.Camera.name -> {
									if (permissionCheckResult == PackageManager.PERMISSION_GRANTED)
										cameraLauncher.launch(uri)
									else
										permissionLauncher.launch(Manifest.permission.CAMERA)
								}
								else -> navController.navigate(it.route)
							}
						},
						backStackEntry = backStackEntry
					)
				}
			}
		) {
			NavigationHost(navController, modifier = Modifier.padding(it))
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun NavigationBottomBar(
	items: List<NavigationItem>,
	onItemClick: (NavigationItem) -> Unit,
	backStackEntry: State<NavBackStackEntry?>,
) {
	BottomNavigation(
		backgroundColor = Color.White,
		modifier = Modifier
			.customShadow()
			.clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
	) {
		items.forEach {
			val selectedItem = it.route == backStackEntry.value?.destination?.route
			BottomNavigationItem(
				selected = selectedItem,
				onClick = { onItemClick.invoke(it) },
				selectedContentColor = PinkBrown,
				unselectedContentColor = Color.Black,
				icon = {
					when {
						it.badgeCount > 0 -> {
							BadgedBox(badge = {
								Text(text = it.badgeCount.toString())
							}) {
								Icon(
									painter = painterResource(id = it.icon),
									contentDescription = null,
									modifier = Modifier.size(25.dp)
								)
							}
						}

						it.route == Screen.Me.name -> {
							MyProfilePic(
								modifier = Modifier
									.size(25.dp)
									.clip(CircleShape)
									.border(BorderStroke(2.dp, PurpleGrey40), shape = CircleShape)
							)
						}

						else -> {
							Icon(
								painter = painterResource(id = it.icon),
								contentDescription = null,
								modifier = Modifier.size(25.dp)
							)
						}
					}
				})
		}
	}
}

@Composable
fun MyProfilePic(modifier: Modifier = Modifier) {
	Image(
		painter = painterResource(R.drawable.person),
		contentDescription = null,
		contentScale = ContentScale.Crop,
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun ActionBar() {
	Row(
		modifier = Modifier
			.customShadow()
			.fillMaxWidth()
			.wrapContentHeight()
			.background(Color.White)
			.padding(10.dp)
	) {
		Spacer(modifier = Modifier.width(10.dp))

		Text(
			text = "ME!",
			fontFamily = anton,
			fontSize = 24.sp,
		)

		Spacer(modifier = Modifier.weight(1f))

		Row(
			modifier = Modifier
				.wrapContentHeight()
				.align(Alignment.CenterVertically)
		) {
			Spacer(modifier = Modifier.width(10.dp))

			Image(
				painter = painterResource(id = R.drawable.ic_chat),
				contentDescription = null,
				modifier = Modifier.size(20.dp)
			)

			Spacer(modifier = Modifier.width(10.dp))
		}
	}
}