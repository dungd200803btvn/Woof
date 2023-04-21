
package com.example.woof

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.data.Dog
import com.example.woof.data.dogs
import com.example.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                WoofApp()
            }
        }
    }
}

/**
 * Composable that displays an app bar and a list of dogs.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WoofApp() {
    Scaffold(
        topBar = {
            WoofAppTopBar()
        }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(dogs) {
                DogItem(dog = it)
            }
        }
    }
}
@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    val expanded by remember {
        mutableStateOf(false)
    }
    Card(modifier = modifier.padding(8.dp)) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(modifier = Modifier.weight(1f))
                DogItemButton(expanded = expanded, onClick = {  })
            }
            DogHobby(dogHobby = dog.hobbies)
        }
        
    }

}
@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
  IconButton(onClick = onClick) {
      Icon(imageVector = Icons.Filled.ExpandMore,
      tint = MaterialTheme.colors.secondary,
      contentDescription = stringResource(id = R.string.expand_button_content_description))

  }
}


@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),
        /*
         * Content Description is not needed here - image is decorative, and setting a null content
         * description allows accessibility services to skip this element during navigation.
         */
        contentDescription = null
    )
}

@Composable
fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(dogName),
             style = MaterialTheme.typography.h2,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            style = MaterialTheme.typography.body1,
            text = stringResource(R.string.years_old, dogAge)
        )
    }
}

@Composable
fun WoofAppTopBar(modifier: Modifier =Modifier){
    Row(modifier = modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.primary),
    verticalAlignment = Alignment.CenterVertically){
        Image(painter = painterResource(id =R.drawable.ic_woof_logo ), contentDescription = null,
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp))
      Text(text = stringResource(id = R.string.app_name),
          style =  MaterialTheme.typography.h1
      )
      }
    }
@Composable
fun DogHobby(@StringRes dogHobby:Int,
modifier:Modifier=Modifier){
    Column(modifier = modifier.padding(start = 16.dp,
        top = 8.dp,
        bottom = 16.dp,
        end = 16.dp)) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = stringResource(dogHobby),
            style = MaterialTheme.typography.body1,
        )
    }
}


@Preview
@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = false) {
        WoofApp()
    }
}
