package com.example.myshoppinglist



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: listViewModel
) {
    val wishes by viewModel.getAllWishes.collectAsState(initial = emptyList())

    val context= LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Shopping List") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddEditScreen.route + "/0")
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Wish")
            }
        }
            ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(wishes, key = { it.id }) { wish ->
                ListItem(
                    wish.id,
                    listitem = wish,
                    onClick = {
                        navController.navigate(Screens.AddEditScreen.route + "/${wish.id}")
                    },
                    onDelete = {
                        viewModel.delete(wish)
                    },
                    viewModel
                )
            }
        }
    }
}
@Composable
fun ListItem(
    id:Long,
    listitem: ListEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    viewModel: listViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = listitem.name, style = MaterialTheme.typography.titleMedium)
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    var quantity by remember { mutableStateOf(listitem.quant) }
                    Text("Quantity:")
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Minus",
                        modifier = Modifier.padding(horizontal = 8.dp).clickable {
                            if (quantity > 0) {
                                quantity--
                                viewModel.updateWish(
                                    ListEntity(
                                        id = id,
                                        name = listitem.name,
                                        quant = quantity,
                                        purchased = listitem.purchased,
                                        createdAt = System.currentTimeMillis()
                                    )
                                )
                            }
                        })
                    Text(
                        text = "${quantity}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Add",
                        modifier = Modifier.padding(horizontal = 8.dp).clickable {
                            quantity++

                            viewModel.updateWish(
                                ListEntity(
                                    id = id,
                                    name = listitem.name,
                                    quant = quantity,
                                    purchased = listitem.purchased,
                                    createdAt = System.currentTimeMillis()
                                )
                            )
                        }
                    )

                }
                Text(text = "Purchased: ${(listitem.purchased)}", style = MaterialTheme.typography.bodyMedium)
                val createdAt = Instant.fromEpochMilliseconds(listitem.createdAt)
                val now = Clock.System.now()
                val timeDifference = remember { mutableStateOf(0L) }
                timeDifference.value = (now - createdAt).inWholeMinutes
                if(timeDifference.value==0L) Text(text="Just Now",style=MaterialTheme.typography.bodySmall)
                else if(timeDifference.value==1L) Text(text = "${timeDifference.value} minute ago", style = MaterialTheme.typography.bodySmall)
                else if(timeDifference.value<60) Text(text = "${timeDifference.value} minutes ago", style = MaterialTheme.typography.bodySmall)
                else if(timeDifference.value<1440) Text(text = "${timeDifference.value/60} hours ago", style = MaterialTheme.typography.bodySmall)
                else Text(text = "${timeDifference.value/1440} days ago", style = MaterialTheme.typography.bodySmall)

            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
