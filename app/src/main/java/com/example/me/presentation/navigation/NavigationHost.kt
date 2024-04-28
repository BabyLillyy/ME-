package com.example.me.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.me.presentation.screen.Dashboard
import com.example.me.presentation.screen.MyEditPost
import com.example.me.presentation.screen.MyFavourite
import com.example.me.presentation.screen.MyPostDetailScreen
import com.example.me.presentation.screen.MyProfile
import com.example.me.presentation.screen.MySearch
import com.example.me.presentation.screen.SplashScreen
import com.example.me.presentation.utils.Screen

@Composable
fun NavigationHost(
	navController: NavHostController,
	modifier: Modifier = Modifier,
) {
	NavHost(
		modifier = modifier,
		navController = navController,
		startDestination = Screen.SplashScreen.name
	) {

		composable(route = Screen.SplashScreen.name) {
			SplashScreen(onLoginClicked = {
				navController.navigate(Screen.Dashboard.name) {
					popUpTo(Screen.SplashScreen.name) { inclusive = true }
				}
			})
		}

		composable(route = Screen.Dashboard.name) {
			Dashboard(onItemClick = {
				navController.navigate("${Screen.CardDetail}/${it}")
			})
		}

		composable(route = Screen.Search.name) {
			MySearch()
		}

		composable(route = Screen.Favourite.name) {
			MyFavourite()
		}

		composable(route = Screen.Me.name) {
			MyProfile()
		}

		composable(
			route = "${Screen.CardDetail}/{$myDetailArg}",
			arguments = listOf(navArgument(myDetailArg) { type = NavType.StringType })
		) {
			MyPostDetailScreen(it.arguments?.getString(myDetailArg)) {
				navController.popBackStack()
			}
		}

		composable(
			route = "${Screen.EditPost}/{$uriCapture}",
			arguments = listOf(navArgument(uriCapture) { type = NavType.StringType })
		) {

			val uri = it.arguments?.let {uri ->
				uri.getString(uriCapture)
					?.replace('|','%')
					?.let(Uri::parse)
			}

			MyEditPost(navController, uri)
		}

		composable(route = Screen.Maps.name) {
//			Maps()
		}
	}
}

private const val myDetailArg = "MY_DETAIL_ARG"
private const val uriCapture = "URI_CAPTURE"