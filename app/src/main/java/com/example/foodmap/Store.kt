package com.example.foodmap

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Store (
    @DrawableRes val pictureId: Int,
    @DrawableRes val menuId: Int,
    @StringRes val nameId: Int,
    @StringRes val addressId: Int,
)

