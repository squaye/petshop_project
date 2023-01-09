package com.samq.petshop

import ShopItem
import ShopItemViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.samq.petshop.ui.theme.PetShopTheme
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samq.petshop.views.*
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetShopTheme {
                // A surface container using the 'background' color from the theme
//                MyApp(modifier = Modifier.fillMaxSize())
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }

        }
    }

    @Composable
    fun MyAppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = "home",
        shopItemViewModel: ShopItemViewModel = viewModel()
    ) {
        val context= LocalContext.current
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("home") {
                HomeView(
                    modifier = modifier,
                    onClick= fun(shopItem: ShopItem) {
                        println(shopItem)
                        println(shopItem.selected)
                        shopItemViewModel.selectItem(shopItem, !shopItem.selected)
                    },
                    onCartClick= {
                        navController.navigate("cart")
                    },
                    onOrderClick={
                        navController.navigate("order")
                    },
                    shopItemViewModel=shopItemViewModel
                )
            }
            composable("order") {
                OrderView(
                    modifier=modifier,
                    onBack = {
                        navController.navigate("home"){
                            popUpTo("home")
                            launchSingleTop = true
                        }
                    },
                    shopItemViewModel=shopItemViewModel
                )
            }
            composable("cart") {
                CartView(
                    modifier=modifier,
                    onClose = fun(shopItem: ShopItem) {
                        println(shopItem)
                        println(shopItem.selected)
                        shopItemViewModel.remove(shopItem)
                    },
                    onCheckClick = {
                        navController.navigate("checkout")
                    },
                    onBack = {
                        navController.navigate("home"){
                            popUpTo("home")
                            launchSingleTop = true
                        }
                    },
                    shopItemViewModel=shopItemViewModel
                )
            }
            composable("checkout") {
                CheckoutView(
                    modifier=modifier,
                    onFinishClick = {
                        val order=shopItemViewModel.order
                        if(order.name.isEmpty()||order.cardNumber.isEmpty()){
                            Toast.makeText(context, "Name and Card number are required", Toast.LENGTH_LONG).show()
                            return@CheckoutView
                        }
                        shopItemViewModel.updateOrder()
                        navController.navigate("thanks")
                    },
                    onBack = {
                        shopItemViewModel.order.date= Date()
                        navController.navigate("cart"){
                            popUpTo("cart")
                            launchSingleTop = true
                        }
                    },
                    shopItemViewModel=shopItemViewModel
                ) }
            composable("thanks") { ThankYouView(
                modifier=modifier,
                onBack = {
                    shopItemViewModel.clear();
                    navController.navigate("home"){
                        popUpTo("home")
                        launchSingleTop = true
                    }
                },
                shopItemViewModel=shopItemViewModel
            ) }
        }
    }


}