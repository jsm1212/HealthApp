package com.example.healthapp

import android.os.Parcel
import android.os.Parcelable

class CalendarDto(val calendarseq:Int, val title:String?,  val content:String? ,val wdate:String?
, val del:Int, val id:String?, val calendardate:String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(calendarseq)
        parcel.writeString(title)
        parcel.writeString(wdate)
        parcel.writeString(content)
        parcel.writeInt(del)
        parcel.writeString(id)
        parcel.writeString(calendardate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalendarDto> {
        override fun createFromParcel(parcel: Parcel): CalendarDto {
            return CalendarDto(parcel)
        }

        override fun newArray(size:  Int): Array<CalendarDto?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return super.toString()
    }
}