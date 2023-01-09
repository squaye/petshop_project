package com.samq.petshop.views

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
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samq.petshop.models.Order
import com.samq.petshop.widgets.OrderWidget
import com.samq.petshop.widgets.ShopItemCartGrid


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderView(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = { OrderTopBar(modifier = Modifier, onBack=onBack, shopItemViewModel=shopItemViewModel) },
        content = {padding ->
            OrderBody(modifier = modifier.padding(padding), shopItemViewModel=shopItemViewModel)
        },
        containerColor = Color.DarkGray
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUnitApi::class)
@Composable
fun OrderTopBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
){
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Orders", color = Color.White)
        },
        navigationIcon = {
            IconButton(
                onClick = onBack,
                modifier=modifier,
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        // background color of topAppBar
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF0F9D58))
    )
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun OrderBody(modifier: Modifier = Modifier,
             shopItemViewModel: ShopItemViewModel = viewModel()
){
    if(shopItemViewModel.isOrdered()){
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "There can only be one order for this demo",
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
                        Text(text = "No of Orders", fontSize = TextUnit(18f, TextUnitType.Sp))
                        Text(text = "${if(shopItemViewModel.isOrdered()) 1 else 0}", fontSize = TextUnit(34f, TextUnitType.Sp))
                    }
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Total Orderd Price", fontSize = TextUnit(18f, TextUnitType.Sp))
                        Text(text = "${if(shopItemViewModel.isOrdered())shopItemViewModel.order.totalPrice else 0}", fontSize = TextUnit(34f, TextUnitType.Sp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            OrderWidget(order = shopItemViewModel.order)
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement= Arrangement.Center,
        ) {
            Text(text = "You have not made any orders",
                modifier= Modifier.padding(vertical = 4.dp),
                style = TextStyle(color = Color.White, fontSize = TextUnit(14f, TextUnitType.Sp))
            )
        }
    }

}
