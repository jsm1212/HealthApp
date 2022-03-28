package com.example.healthapp.work

import android.os.Parcel
import android.os.Parcelable

class WorkVo(val name:String?,val explanation:String?, val photo:String?, val workcategory:Int)
    :Parcelable {
    constructor(parcel:Parcel):this(parcel.readString(), parcel.readString(),
        parcel.readString(), parcel.readInt())

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(name)
        parcel.writeString(explanation)
        parcel.writeString(photo)
        parcel.writeInt(workcategory)
    }
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<WorkVo>{
        override fun createFromParcel(parcel: Parcel): WorkVo {
            return WorkVo(parcel)
        }

        override fun newArray(size: Int): Array<WorkVo?> {
            return arrayOfNulls(size)
        }
    }

}