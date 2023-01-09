package com.samq.petshop.views

import ShopItem
import ShopItemViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material.icons.sharp.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samq.petshop.ui.theme.PetShopTheme
import com.samq.petshop.widgets.ShopItemGrid


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onClick: (ShopItem) -> Unit,
    onCartClick: () -> Unit,
    onOrderClick: ()->Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(modifier = Modifier, onCartClick = onCartClick, onOrderClick = onOrderClick, shopItemViewModel=shopItemViewModel) },
        content = {padding ->
            Body(modifier = modifier.padding(padding), onClick = onClick, shopItemViewModel=shopItemViewModel)
        },
        containerColor = Color.DarkGray
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUnitApi::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit,
    onOrderClick: ()->Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
){
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Pet Shop", color = Color.White)
        },
        actions= {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Menu",
                modifier = Modifier.clickable { onOrderClick() },
                tint = if(shopItemViewModel.isOrdered())Color.Red else Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
                    ){
                Icon(
                    imageVector = Icons.Sharp.ShoppingCart,
                    contentDescription = "Menu",
                    modifier = Modifier.clickable { onCartClick() },
                    tint = Color.White
                )
                if (shopItemViewModel.selectedItems.isNotEmpty()) {
                    Text("${shopItemViewModel.selectedItems.count()}.",
                        modifier=modifier.paddingFromBaseline(bottom = 14.dp),
                        style = TextStyle(color=Color.White, fontSize = TextUnit(16f, TextUnitType.Sp)))
                }
            }

        },
        // background color of topAppBar
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF0F9D58))
    )
}

@Composable
fun Body(modifier: Modifier = Modifier,
         onClick:(ShopItem) -> Unit,
         shopItemViewModel: ShopItemViewModel = viewModel()){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Click on cart icon on the top right to proceed",
            modifier=Modifier.padding(vertical = 4.dp),
            style = TextStyle(color = Color.White)
        )
        ShopItemGrid(
            list = shopItemViewModel.items,
            onClick = onClick,
            modifier = modifier
        )
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    PetShopTheme {
        HomeView(onClick = {}, onCartClick = { /*TODO*/ }, onOrderClick = {})
    }
}