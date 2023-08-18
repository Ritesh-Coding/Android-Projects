package com.example.android.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.composable.ui.theme.composableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            composableTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TriviaWeek(name = "Ritesh Jaiswal")
                    //                    Greeting("Android")
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}

@Composable
fun TriviaWeek(name: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.compose_camp_logo),
            contentDescription = "Compose Camp", modifier = Modifier.size(200.dp)
        )
        Text(
            text = "$name",
            color = colorResource(id = R.color.name_color),
            fontSize = 34.sp, fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 30.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.red_heart),
            contentDescription = "red_heart", modifier = Modifier
                .size(130.dp)
                .padding(top = 33.dp)
        )
        Text(
            text = "Jetpack Compose",
            color = colorResource(id = R.color.jetpack),
            fontSize = 38.sp, fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            modifier = Modifier.padding(top = 30.dp)
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    composableTheme {
        TriviaWeek(name = "Ritesh Jaiswal")
        //        Greeting("Android")
    }
}