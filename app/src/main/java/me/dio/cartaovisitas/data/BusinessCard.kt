package me.dio.cartaovisitas.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String?,
    val empresa: String?,
    val telefone: String?,
    val email: String?,
    val fundoPersonalizado: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nome)
        parcel.writeString(empresa)
        parcel.writeString(telefone)
        parcel.writeString(email)
        parcel.writeString(fundoPersonalizado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BusinessCard> {
        override fun createFromParcel(parcel: Parcel): BusinessCard {
            return BusinessCard(parcel)
        }

        override fun newArray(size: Int): Array<BusinessCard?> {
            return arrayOfNulls(size)
        }
    }
}