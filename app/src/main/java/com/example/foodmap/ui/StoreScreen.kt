package com.example.foodmap

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun StoreApp(
    @StringRes nameId: Int,
    @StringRes addressId: Int = 0,
    @DrawableRes imageId: Int = 0,
    @DrawableRes menuId: Int = 0,
    navController: NavHostController = rememberNavController()
) {
    val storeViewModel: StoreViewModel = viewModel()
    Scaffold(
        topBar = { StoreInfoTopBar(nameId, storeViewModel, navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            item {
                StoreImage(nameId = nameId, imageId = imageId)
            }
            item {
                InfoCard(titleId = R.string.info_1, messageId = addressId)
            }
            if (menuId!=0) {
                item {
                    InfoCard(titleId = R.string.info_2, imageId = menuId)
                }
            }

            item {
                StoreMap(addressId)
            }
        }
    }
}

@Composable
private fun StoreImage(@StringRes nameId: Int, @DrawableRes imageId: Int) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = stringResource(id = nameId),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_large),
                        vertical = dimensionResource(R.dimen.padding_medium)
                    )
                    .fillMaxWidth()
            )
        }
        Text(
            text = stringResource(id = nameId),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun InfoCard(@StringRes titleId: Int, @StringRes messageId: Int = 0, @DrawableRes imageId: Int = 0) {
    var expand by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { expand = !expand },
    ) {
        Column {
            Text(
                text = stringResource(id = titleId),
                modifier = Modifier.padding(8.dp)
            )
            if (expand && messageId != 0) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = messageId),
                    modifier = Modifier.padding(8.dp)
                )
            }
            if (expand && imageId != 0) {
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun StoreMap( @StringRes addressId: Int){
    val context = LocalContext.current
    val searchQuery = Uri.encode(stringResource(id = addressId))
    val gmmIntentUri = Uri.parse("geo:0,0?q=$searchQuery")
    var connectMap by remember { mutableStateOf(false) }

    Button(
        onClick = {
            connectMap = true
        },
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(text = "Open in Google Maps")
    }

    if (connectMap) {
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        context.startActivity(mapIntent)
        connectMap = false
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StoreInfoTopBar(@StringRes nameId: Int, storeViewModel: StoreViewModel, navController: NavHostController) {
    TopAppBar(
        title = { Text(text = stringResource(id = nameId)) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}


