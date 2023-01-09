package com.samq.petshop.widgets

import ShopItem
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.R
import coil.compose.rememberAsyncImagePainter
import com.samq.petshop.ui.theme.PetShopTheme


@OptIn(ExperimentalUnitApi::class)
@Composable
fun ShopItemWidget(
    item: ShopItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                ,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .height(120.dp)

                )
                Text(
                    text = item.name,
                    //style=MaterialTheme.typography.h3,
                    modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp).background(Color.Transparent)
                )
                Text(
                    text = item.type,
                    style=MaterialTheme.typography.bodySmall,
                    modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp).background(Color.Transparent)
                )
                Text(
                    text = "Ghc ${item.price}",
                    style=MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp).background(Color.Transparent)
                )
            }
        }
        Button(
            onClick = onClick,
            modifier= Modifier.width(120.dp).height(40.dp).padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = if(item.selected) Color.Red else Color.Cyan)) {
            Text(if(item.selected) "Remove" else "Buy", color = Color.Black, style = TextStyle(fontSize = TextUnit(
                12F, TextUnitType.Sp)))
        }
    }
}


@Composable
fun ShopItemCartWidget(
    item: ShopItem,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = item.name
        )
        Text(
            modifier = Modifier
                .padding(start = 16.dp),
            text = "Ghc ${item.price}"
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    PetShopTheme {
        ShopItemCartWidget(item = ShopItem(1,300.0,"Dog", "Black Dog", "Pet", "", false), onClose = { /*TODO*/ })
    }
}