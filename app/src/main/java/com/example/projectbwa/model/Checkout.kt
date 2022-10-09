package com.example.projectbwa.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Checkout (
    var kursi:String ? = "",
    var harga:String ? = ""
) : Parcelable


