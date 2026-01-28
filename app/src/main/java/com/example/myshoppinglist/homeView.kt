package com.example.myshoppinglist



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Scaffold
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
                    listitem = wish,
                    onClick = {
                        navController.navigate(Screens.AddEditScreen.route + "/${wish.id}")
                    },
                    onDelete = {
                        viewModel.delete(wish)
                    }
                )
            }
        }
    }
}
@Composable
fun ListItem(
    listitem: ListEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit
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
                Text(text = "Quantity: ${listitem.quant}", style = MaterialTheme.typography.bodyMedium)
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
