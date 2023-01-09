package com.samq.petshop.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samq.petshop.models.Order


@Composable
fun OrderWidget(
    order: Order,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = modifier.background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                text = "${order.date}"
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = "Status: ${order.status}"
            )

        }
        Row(
            modifier = modifier.background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                text = "Total items: ${order.totalItems}"
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = "Ghc ${order.totalPrice}"
            )

        }
    }
}
