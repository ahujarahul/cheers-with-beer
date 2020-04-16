package com.rahulahuja.cheerswithbeer.presentation.models

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class BeerDetailUI(
        val image: String?,
        val foodPairing: ArrayList<String>?
) : Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.createStringArrayList()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(image)
                parcel.writeStringList(foodPairing)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<BeerDetailUI> {
                override fun createFromParcel(parcel: Parcel): BeerDetailUI {
                        return BeerDetailUI(parcel)
                }

                override fun newArray(size: Int): Array<BeerDetailUI?> {
                        return arrayOfNulls(size)
                }
        }
}