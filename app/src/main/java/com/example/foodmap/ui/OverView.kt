package com.example.foodmap.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.foodmap.R
import com.example.foodmap.Store
import com.example.foodmap.StoreViewModel

@Composable
fun FoodMapApp(navController: NavHostController) {
    val storeViewModel: StoreViewModel = viewModel()
    Scaffold(
        topBar = { FoodMapTopBar() }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            AllStoresLayout(storeViewModel = storeViewModel, navController = navController)
        }
    }
}

@Composable
private fun AllStoresLayout(storeViewModel: StoreViewModel, navController: NavHostController) {
    val stores by storeViewModel.stores.collectAsState()
    LazyColumn {
        items(stores) { store ->
            StoreCard(store = store, navController = navController)
        }
    }
}
@Composable
private fun StoreCard(store: Store, modifier: Modifier = Modifier, navController: NavHostController) {
    Card(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .clickable {
                navController.navigate("detail/${store.nameId}/${store.addressId}/${store.pictureId}/${store.menuId}")
            }
    ) {
        Row {
            Box {
                Image(
                    painter = painterResource(id = store.pictureId),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_medium),
                            start = dimensionResource(id = R.dimen.padding_medium),
                            end = dimensionResource(id = R.dimen.padding_medium),
                        )
                        .size(150.dp)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = store.nameId),
                modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.padding_small)),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoodMapTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .fillMaxWidth()
                    .height(90.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(85.dp)
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource
                        (R.drawable.icon),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
    )
}
