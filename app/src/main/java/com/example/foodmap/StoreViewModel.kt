package com.example.foodmap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private val storeImageList: List<Int> =
    listOf(
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_4,
        R.drawable.image_5,
        R.drawable.image_6,
        R.drawable.image_7,
        R.drawable.image_8,
        R.drawable.image_9,
        R.drawable.image_10,
    )

private val menuList: List<Int> =
    listOf(
        R.drawable.menu_1,
        0,
        R.drawable.menu_3,
        R.drawable.menu_4,
        R.drawable.menu_5,
        0,
        R.drawable.menu_7,
        0,
        R.drawable.menu_9,
        0,
    )

class StoreViewModel : ViewModel() {
    private val _stores = MutableStateFlow<List<Store>>(emptyList())
    val stores: StateFlow<List<Store>> = _stores

    init {
        viewModelScope.launch {
            _stores.value = loadStores()
        }
    }

    private fun loadStores(): List<Store> {
            val stores: List<Store> =
                listOf(
                    Store(
                        pictureId = storeImageList[0],
                        menuId = menuList[0],
                        nameId = R.string.storeName_1,
                        addressId = R.string.storeAddress_1,
                    ),
                    Store(
                        pictureId = storeImageList[1],
                        menuId = menuList[1],
                        nameId = R.string.storeName_2,
                        addressId = R.string.storeAddress_2,
                    ),
                    Store(
                        pictureId = storeImageList[2],
                        menuId = menuList[2],
                        nameId = R.string.storeName_3,
                        addressId = R.string.storeAddress_3,
                        ),
                    Store(
                        pictureId = storeImageList[3],
                        menuId = menuList[3],
                        nameId = R.string.storeName_4,
                        addressId = R.string.storeAddress_4,

                    ),
                    Store(
                        pictureId = storeImageList[4],
                        menuId = menuList[4],
                        nameId = R.string.storeName_5,
                        addressId = R.string.storeAddress_5,
                    ),
                    Store(
                        pictureId = storeImageList[5],
                        menuId = menuList[5],
                        nameId = R.string.storeName_6,
                        addressId = R.string.storeAddress_6,
                    ),
                    Store(
                        pictureId = storeImageList[6],
                        menuId = menuList[6],
                        nameId = R.string.storeName_7,
                        addressId = R.string.storeAddress_7,
                    ),
                    Store(
                        pictureId = storeImageList[7],
                        menuId = menuList[7],
                        nameId = R.string.storeName_8,
                        addressId = R.string.storeAddress_8,
                    ),
                    Store(
                        pictureId = storeImageList[8],
                        menuId = menuList[8], nameId = R.string.storeName_9,
                        addressId = R.string.storeAddress_9,
                    ),
                    Store(
                        pictureId = storeImageList[9],
                        menuId = menuList[9],
                        nameId = R.string.storeName_10,
                        addressId = R.string.storeAddress_10,
                    )
                )
        return stores
    }
}


