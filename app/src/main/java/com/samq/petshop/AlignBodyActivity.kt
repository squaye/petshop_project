package com.samq.petshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samq.petshop.ui.theme.PetShopTheme
import java.util.*

class AlignBodyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySootheApp() {
    PetShopTheme{
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(modifier) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text("Home")
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text("Profile")
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier=Modifier){
    Column(modifier) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = "Align Your Body") {
            AlignYourBodyRow()
        }
        HomeSection(title = "Favourite Collection") {
            FavouriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)) {
        Text(text = title.uppercase(Locale.getDefault()),
//                style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp))
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxSize()
            .heightIn(min = 56.dp),
        leadingIcon={ Icon(imageVector = Icons.Default.Search,contentDescription = null) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text("search")
        },
    )
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier=Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier){
//            items(alignYourBodyData){
//                item->AlignYourBodyElement(item.drawable, item.text)
//            }
    }
}

@Composable
fun FavouriteCollectionsGrid(
    modifier: Modifier=Modifier
){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier
    ){
//            items(favoriteCollectionsData){
//                item->FavoriteCollectionCard(
//                    item.drawable,
//                    item.text,
//                    modifier = Modifier.height(56.dp)
//                )
//            }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
){
    Column (
        horizontalAlignment= Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Image(
            painter = painterResource(androidx.core.R.drawable.notification_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Inversions",
            //style=MaterialTheme.typography.h3,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier
){
    Surface (
        shape = MaterialTheme.shapes.small,
        modifier=modifier
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription =null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(text = text)
        }
    }
}

@Preview(showBackground =true, backgroundColor = 0xFF0EAE2)
@Composable
fun AlignYourBodyElementPreview(){
    PetShopTheme{
        AlignYourBodyElement(
            text=R.string.title_activity_basic,
            drawable = androidx.core.R.drawable.notification_bg,
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    PetShopTheme {
        FavoriteCollectionCard(
            text = "Nature Meditations",
            drawable = R.drawable.ic_launcher_background,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    PetShopTheme {
        HomeSection("Home") {
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    PetShopTheme { HomeScreen() }
}