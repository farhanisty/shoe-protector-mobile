package com.example.shoeprotector.ui

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoeprotector.ui.components.DeleteCardDialog
import com.example.shoeprotector.viewmodel.CardViewModel
import com.example.shoeprotector.viewmodel.FetchCardState
import io.ktor.util.reflect.instanceOf
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardHomeScreen(onAddRegister: () -> Unit, scaffoldPadding: PaddingValues,modifier: Modifier = Modifier, cardViewModel: CardViewModel = koinViewModel()) {
    val state by cardViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        cardViewModel.initView()
    }

    if(state.showDeleteDialog) {
        DeleteCardDialog(
            onDelete = {
                cardViewModel.deleteCard()
            },
            onDismiss =  {
                cardViewModel.closeDeleteDialog()
            }
        )
    }

    Column(
        modifier = Modifier
            .padding(scaffoldPadding)
    ) {
        OutlinedButton(onClick = {
            onAddRegister()
        },
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Tambah Card")
                Spacer(modifier = Modifier.width(12.dp))
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        when(val s = state.fetchCardState) {
            is FetchCardState.Loading -> {
                CircularProgressIndicator()
            }

            is FetchCardState.Success -> {
                LazyColumn {
                    items(s.cards) { card ->
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = MaterialTheme.shapes.medium
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(12.dp)
                                            .weight(1f)
                                    ) {
                                        Text(
                                            card.name,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                        )

                                        Text(
                                            "ID: ${card.id}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Light,
                                            color = Color.DarkGray
                                        )
                                    }

                                    Row {
                                        FilledIconButton(
                                            onClick = {},
                                            shape = RoundedCornerShape(12.dp),
                                            colors = IconButtonColors(
                                                Color.Blue,
                                                Color.White,
                                                Color.DarkGray,
                                                Color.Gray
                                            ),
                                            modifier = Modifier
                                                .size(48.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.Edit,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(28.dp)
                                            )
                                        }

                                        Spacer(
                                            modifier = Modifier
                                                .width(8.dp)
                                        )

                                        FilledIconButton(
                                            onClick = {
                                                cardViewModel.showDeleteDialog(card.id)
                                            },
                                            shape = RoundedCornerShape(12.dp),
                                            colors = IconButtonColors(
                                                Color.Red,
                                                Color.White,
                                                Color.DarkGray,
                                                Color.Gray
                                            ),
                                            modifier = Modifier
                                                .size(48.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.Delete,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(28.dp)
                                            )
                                        }
                                    }
                                }
                                Divider(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    color = Color.Blue,
                                    thickness = 1.dp
                                )

                                OutlinedButton(
                                    onClick = {},
                                    colors = ButtonColors(
                                        Color.Yellow,
                                        Color.DarkGray,
                                        Color.DarkGray,
                                        Color.LightGray
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text("View Log")
                                }
                            }
                        }
                    }
                }
            }

            is FetchCardState.Error -> {
                Text("Error")
            }
        }
    }
}