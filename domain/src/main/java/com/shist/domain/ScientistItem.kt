package com.shist.domain

import android.os.Parcel
import android.os.Parcelable

data class ScientistItem(
    val id: String,
    val fullName: String?,
    val imagePath: String?,
    val description: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(fullName)
        parcel.writeString(imagePath)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScientistItem> {
        override fun createFromParcel(parcel: Parcel): ScientistItem {
            return ScientistItem(parcel)
        }

        override fun newArray(size: Int): Array<ScientistItem?> {
            return arrayOfNulls(size)
        }
    }
}