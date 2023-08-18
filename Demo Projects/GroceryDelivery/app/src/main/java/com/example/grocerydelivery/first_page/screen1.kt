package com.example.grocerydelivery.first_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import android.provider.SyncStateContract
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.text.font.FontWeight
import com.example.grocerydelivery.R



@Preview(showSystemUi = true  , showBackground = true)
@Composable
fun Onboarding() {           //if we do not write column then one and two will overlap to each other
//    Column(){
//        Text(text = "one")
//        Text(text = "two")

//    }

    Column(modifier= Modifier         //to modify the images
        .fillMaxSize()
        .padding(24.dp)) {

        Image(painter = painterResource(id =R.drawable.fruit_logo), contentDescription = "logo")



        Text(text = "Quick Delivery At", fontWeight = FontWeight.ExtraBold)

    }
}
class screen1 {
}