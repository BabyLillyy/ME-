package com.example.me.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.me.R

// Set of Material typography styles to start with
val Typography = Typography(
	bodyLarge = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	)
	/* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val sendCatFont = FontFamily(
	Font(R.font.sendcat)
)
val anton = FontFamily(
	Font(R.font.antonregular)
)

val inter = FontFamily(
	Font(R.font.inter)
)

val anton10 = TextStyle(
	fontFamily = anton,
	fontSize = 10.sp
)

val anton15 = TextStyle(
	fontFamily = anton,
	fontSize = 15.sp
)

val anton20 = TextStyle(
	fontFamily = anton,
	fontSize = 20.sp
)

val anton25 = TextStyle(
	fontFamily = anton,
	fontSize = 25.sp,
)

val anton30 = TextStyle(
	fontFamily = anton,
	fontSize = 30.sp,
)

val anton40 = TextStyle(
	fontFamily = anton,
	fontSize = 40.sp,
)

val inter10 = TextStyle(
	fontFamily = inter,
	fontSize = 10.sp
)

val inter12 = TextStyle(
	fontFamily = inter,
	fontSize = 12.sp
)

val inter15 = TextStyle(
	fontFamily = inter,
	fontSize = 15.sp
)

val inter20 = TextStyle(
	fontFamily = inter,
	fontSize = 20.sp
)
