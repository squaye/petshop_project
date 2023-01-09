package com.samq.petshop.views

import ShopItem
import ShopItemViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samq.petshop.widgets.ShopItemCartGrid


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutView(
    modifier: Modifier = Modifier,
    onFinishClick: () -> Unit,
    onBack: () -> Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = { CheckTopBar(modifier = Modifier, onFinishClick = onFinishClick,onBack=onBack, shopItemViewModel=shopItemViewModel) },
        content = {padding ->
            CheckBody(modifier = modifier.padding(padding), shopItemViewModel=shopItemViewModel)
        },
        containerColor = Color.DarkGray
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUnitApi::class)
@Composable
fun CheckTopBar(
    modifier: Modifier = Modifier,
    onFinishClick: () -> Unit,
    onBack: () -> Unit,
    shopItemViewModel: ShopItemViewModel = viewModel()
){
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Payment", color = Color.White)
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
                    onClick=onFinishClick,
                    enabled = shopItemViewModel.selectedItems.isNotEmpty(),
                ){
                    Text(text = "Pay & Complete")
                }
            }
        },
        // background color of topAppBar
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF0F9D58))
    )
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun CheckBody(modifier: Modifier = Modifier,
             shopItemViewModel: ShopItemViewModel = viewModel()
){

        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Am combining personal information and payment for simplicity sake. So this page will be the final page of the buying process. Also payment is going to be just one field to keep it simple",
                modifier= Modifier.padding(vertical = 10.dp),
                style = TextStyle(color = Color.White),
                textAlign = TextAlign.Center,
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
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Personal Information", color = Color.White)
            Divider(thickness = 2.dp, color = Color.White)
            Spacer(modifier = Modifier.height(5.dp))
            NameField(modifier = Modifier.padding(5.dp))
            EmailField(modifier = Modifier.padding(5.dp))
            PhoneField(modifier = Modifier.padding(5.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Payment Information", color = Color.White)
            Divider(thickness = 2.dp, color = Color.White)
            Spacer(modifier = Modifier.height(5.dp))
            CardNumberField(modifier = Modifier.padding(5.dp))
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameField(modifier: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text("full name")
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(modifier: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon={ Icon(imageVector = Icons.Default.Email,contentDescription = null) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text("example@gmail.com")
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneField(modifier: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon={ Icon(imageVector = Icons.Default.Phone,contentDescription = null) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        placeholder = {
            Text("233276xxxxxx")
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentTypeBox(modifier: Modifier){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNumberField(modifier: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        placeholder = {
            Text("3899 3339 xxxx xxxx")
        },
    )
}
