package com.example.fintrack.models

//data class BudgetModel(
//    val expenseValue: String,
//    val savingValue: String,
//    val days: String
//)

import android.os.Parcel
import android.os.Parcelable

data class BudgetModel(
    val expenseValue: String,
    val savingValue: String,
    val days: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(expenseValue)
        parcel.writeString(savingValue)
        parcel.writeString(days)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BudgetModel> {
        override fun createFromParcel(parcel: Parcel): BudgetModel {
            return BudgetModel(parcel)
        }

        override fun newArray(size: Int): Array<BudgetModel?> {
            return arrayOfNulls(size)
        }
    }
}
