package com.samq.petshop.views

import ShopItem
import ShopItemViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.samq.petshop.widgets.ShopItemCartGrid


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(
    modifier: Modifier = Modifier,
    onClose: (ShopItem) -> Unit,
    onCheckClick: () -> Unit,
    onBack: () -> Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = { CartTopBar(modifier = Modifier, onCheckClick = onCheckClick,onBack=onBack, shopItemViewModel=shopItemViewModel) },
        content = {padding ->
            CartBody(modifier = modifier.padding(padding), onClose = onClose, shopItemViewModel=shopItemViewModel)
        },
        containerColor = Color.DarkGray
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUnitApi::class)
@Composable
fun CartTopBar(
    modifier: Modifier = Modifier,
    onCheckClick: () -> Unit,
    onBack: () -> Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
){
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Pet Shop", color = Color.White)
        },
        navigationIcon = {
                         IconButton(
                             onClick = onBack,
                             modifier=modifier,
                         ) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                         }
        },
        actions= {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick=onCheckClick,
                    enabled = shopItemViewModel.selectedItems.isNotEmpty(),
                ){
                    Text(text = "Check Out")
                }
            }
        },
        // background color of topAppBar
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF0F9D58))
    )
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun CartBody(modifier: Modifier = Modifier,
         onClose:(ShopItem) -> Unit,
         shopItemViewModel: ShopItemViewModel = viewModel()
){
    if(shopItemViewModel.selectedItems.isNotEmpty()){
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Click on the checkout button on the top right to proceed",
                modifier= Modifier.padding(vertical = 4.dp),
                style = TextStyle(color = Color.White)
            )
            Surface (
                color = Color.White,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(100.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "No of Items", fontSize = TextUnit(22f, TextUnitType.Sp))
                        Text(text = "${shopItemViewModel.selectedItems.count()}", fontSize = TextUnit(44f, TextUnitType.Sp))
                    }
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Total Price", fontSize = TextUnit(22f, TextUnitType.Sp))
                        Text(text = "${shopItemViewModel.selectedItems.sumOf { it.price }}", fontSize = TextUnit(44f, TextUnitType.Sp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            ShopItemCartGrid(
                list = shopItemViewModel.selectedItems,
                onClose = onClose,
                modifier = modifier
            )
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement= Arrangement.Center,
        ) {
            Text(text = "You do not have any item in the Cart",
                modifier= Modifier.padding(vertical = 4.dp),
                style = TextStyle(color = Color.White, fontSize = TextUnit(14f, TextUnitType.Sp))
            )
        }
    }

}
