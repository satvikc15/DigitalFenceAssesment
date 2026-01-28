package com.example.myshoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun AddEditScreen(
    id : Long,
    viewModel: listViewModel,
    navController: NavController
){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    // Load existing item data when editing
    LaunchedEffect(id) {
        if(id != 0L) {
            viewModel.getListById(id).collect { wish ->
                viewModel.ListName = wish.name
                viewModel.ListQuant = wish.quant.toString()
                viewModel.ListPurchased = wish.purchased
            }
        } else {
            viewModel.ListName = ""
            viewModel.ListQuant = ""
            viewModel.ListPurchased = false
        }
    }
    
    Scaffold(
        topBar = {
            AppBarView(
                title= if (id!=0L) stringResource(R.string.Update) else stringResource(R.string.add),
                onBackNavClicked = { navController.navigateUp() }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Name", 
                value = viewModel.ListName, 
                onValueChanged = {
                    viewModel.onListNameChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Quantity",
                value = viewModel.ListQuant, 
                onValueChanged = {
                    viewModel.onListQuantChange(it)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            wishPurchaseField(
                label = "Purchased",
                value = viewModel.ListPurchased, 
                onValueChanged = {
                    viewModel.onListPurchasedChange(it)
                }
            )

            Button(onClick = {
                if(viewModel.ListName.isNotEmpty() && viewModel.ListQuant.isNotEmpty()) {
                    if (id != 0L) {
                        viewModel.updateWish(
                            ListEntity(
                                id = id,
                                name = viewModel.ListName.trim(),
                                quant = viewModel.ListQuant.trim().toInt(),
                                purchased = viewModel.ListPurchased,
                                createdAt = System.currentTimeMillis()
                            )
                        )
                    } else {
                        viewModel.add(
                            ListEntity(
                                name = viewModel.ListName.trim(),
                                quant = viewModel.ListQuant.trim().toInt(),
                                purchased = viewModel.ListPurchased,
                                createdAt = System.currentTimeMillis()
                            )
                        )
                    }
                    navController.navigateUp()
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar("Please add stuff, fields cannot be empty")
                    }
                }
            }) {
                Text(if (id!=0L) stringResource(R.string.Update) else stringResource(R.string.add))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label:String,
    value:String,
    onValueChanged :(String)->Unit
){
    OutlinedTextField(
        value=value,
        onValueChange=onValueChanged,
        label={Text(text=label,color=Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id=R.color.black),
            unfocusedTextColor = colorResource(id=R.color.black),


            )
    )
}

@Composable
fun wishPurchaseField(
    label:String,
    value:Boolean,
    onValueChanged :(Boolean)->Unit
){
    Row(modifier = Modifier.padding(
        start = 8.dp,
        end = 8.dp,
        bottom = 8.dp
    ).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "$label: $value", color = Color.Black)
        Button(onClick = {
            onValueChanged(!value)
        }) {
            if (value) {
                Text("Purchased")
            } else {
                Text("Not Purchased")
            }
        }
    }
}
@Preview
@Composable
fun WishTextFieldPrev(){
    WishTextField("text","text",{})
}