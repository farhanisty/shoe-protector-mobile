package com.example.shoeprotector.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoeprotector.Screen

@Composable
fun HomeScreen(innerPadding: PaddingValues, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .padding(innerPadding)
    ) {
        Card(
            colors = CardColors(
                Color.Blue,
                Color.White,
                Color.Green,
                Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(
                    "Statistic".toUpperCase(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )

                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical=8.dp),
                    color = Color.White,
                    thickness = 2.dp
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        "Total Attempt : 0"
                    )
                    Text(
                        "Last Attempt : 21 November 2025"
                    )
                }
            }

        }
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end=12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(
                            "Attempt By: Farhannivta",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier
                                .height(12.dp)
                        )

                        Text(
                            "13:12 11 November 2025",
                            fontSize = 10.sp,
                            color = Color.DarkGray
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.Green)
                    ) {
                        Text(
                            "Success",
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 6.dp
                                )
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(PaddingValues(top = 48.dp))
}