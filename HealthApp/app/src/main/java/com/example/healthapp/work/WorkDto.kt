package com.example.healthapp.work

import android.os.Parcel
import android.os.Parcelable

class WorkDto(
    val workseq: Int, val workname: String?, val workcontent: String?
    , val part: Int, val worklike: Int, val workimage: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(), parcel.readString(), parcel.readString(),
        parcel.readInt(), parcel.readInt(),parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(workseq)
        parcel.writeString(workname)
        parcel.writeString(workcontent)
        parcel.writeInt(part)
        parcel.writeInt(worklike)
        parcel.writeString(workimage)
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<WorkDto> {
        override fun createFromParcel(parcel: Parcel): WorkDto {
            return WorkDto(parcel)
        }

        override fun newArray(size: Int): Array<WorkDto?> {
            return arrayOfNulls(size)
        }
    }

}