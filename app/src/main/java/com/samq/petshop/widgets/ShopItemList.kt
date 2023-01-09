package com.samq.petshop.widgets

import ShopItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samq.petshop.MyApp
import com.samq.petshop.ui.theme.PetShopTheme


@Composable
fun ShopItemGrid(
    modifier: Modifier = Modifier,
    onClick: (ShopItem) -> Unit,
    list: List<ShopItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
    ){
            items(list){
                item->
                ShopItemWidget(item = item, onClick = {onClick(item)})
            }
    }
}


@Composable
fun ShopItemCartGrid(
    modifier: Modifier = Modifier,
    onClose: (ShopItem) -> Unit,
    list: List<ShopItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items=list, key = {item->item.id}) { item ->
            ShopItemCartWidget(
                item = item,
                onClose = {onClose(item)},
                modifier=Modifier.padding(2.dp)
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    PetShopTheme {
        MyApp()
    }
}