package com.samq.petshop

import ShopItem
import ShopItemViewModel
import WellnessScreen
import android.os.Bundle
import android.widget.Space
import android.widget.Toast
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
import androidx.compose.material.icons.filled.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samq.petshop.ui.theme.PetShopTheme
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samq.petshop.models.Order
import com.samq.petshop.views.CartView
import com.samq.petshop.views.CheckoutView
import com.samq.petshop.views.HomeView
import com.samq.petshop.views.ThankYouView
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