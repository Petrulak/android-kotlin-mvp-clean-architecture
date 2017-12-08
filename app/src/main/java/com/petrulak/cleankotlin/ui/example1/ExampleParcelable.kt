package com.petrulak.cleankotlin.ui.example1

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@SuppressLint("ParcelCreator")
class ExampleParcelable(val message: String) : Parcelable