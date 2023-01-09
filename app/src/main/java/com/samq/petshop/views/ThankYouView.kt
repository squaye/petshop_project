package com.samq.petshop.views

import ShopItemViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samq.petshop.models.Order


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThankYouView(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = { ThankTopBar(modifier = Modifier, onBack=onBack, shopItemViewModel=shopItemViewModel) },
        content = {padding ->
            ThankBody(modifier = modifier.padding(padding), onBack=onBack, shopItemViewModel=shopItemViewModel)
        },
        containerColor = Color.DarkGray
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUnitApi::class)
@Composable
fun ThankTopBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
){
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Thank You for the purchase", color = Color.White)
        },
        actions= {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Sharp.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .clickable { onBack() }
                        .background(Color.White),
                )
            }
        },
        // background color of topAppBar
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.DarkGray)
    )
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ThankBody(modifier: Modifier = Modifier,
              onBack: () -> Unit,
             shopItemViewModel: ShopItemViewModel = viewModel()
){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

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
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Receipt", color = Color.White)
        Divider(thickness = 2.dp, color = Color.White)
        Spacer(modifier = Modifier.height(5.dp))
        NameText(modifier = Modifier, shopItemViewModel.order)
        EmailText(modifier = Modifier, shopItemViewModel.order)
        PhoneText(modifier = Modifier, shopItemViewModel.order)
        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth()
            .background(Color.White))
        DateText(modifier = Modifier, shopItemViewModel.order)
        StatusText(modifier = Modifier, shopItemViewModel.order )
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick=onBack,
        ){
            Text(text = "Go Back")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameText(modifier: Modifier, order: Order){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
            ){
        Text(text = "Name: ")
        Text(text = order.name)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailText(modifier: Modifier, order: Order){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
    ){
        Text(text = "Email: ")
        Text(text = order.email)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneText(modifier: Modifier, order: Order){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
    ){
        Text(text = "Phone: ")
        Text(text = order.phone)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateText(modifier: Modifier, order: Order){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
    ){
        Text(text = "Date: ")
        Text(text = order.date.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusText(modifier: Modifier, order: Order){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White)
    ){
        Text(text = "Status: ")
        Text(text = order.status)
    }
}